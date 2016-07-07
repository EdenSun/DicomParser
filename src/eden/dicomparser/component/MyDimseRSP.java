package eden.dicomparser.component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.dcm4che2.data.DicomObject;
import org.dcm4che2.net.Association;
import org.dcm4che2.net.DimseRSP;
import org.dcm4che2.net.DimseRSPHandler;

public class MyDimseRSP extends DimseRSPHandler implements DimseRSP {

	private MyEntry entry = new MyEntry(null, null);

	private boolean finished;
	private int autoCancel;
	private IOException ex;

	List<DicomObject> dataList = new ArrayList<DicomObject>();

	@Override
	public synchronized void onDimseRSP(Association as, DicomObject cmd, DicomObject data) {
	        dataList.add( data );
	}
	/*@Override
	public synchronized void onDimseRSP(Association as, DicomObject cmd, DicomObject data) {
		super.onDimseRSP(as, cmd, data);
		MyEntry last = entry;
		while (last.next != null)
			last = last.next;

		last.next = new MyEntry(cmd, data);
		if (CommandUtils.isPending(cmd)) {
			if (autoCancel > 0 && --autoCancel == 0)
				try {
					super.cancel(as);
				} catch (IOException e) {
					ex = e;
				}
		} else {
			finished = true;
		}
		notifyAll();
	}*/

	@Override
	public synchronized void onClosed(Association as) {
		if (!finished) {
			// ex = as.getException();
			ex = null;
			if (ex == null) {
				ex = new IOException(
						"Association to " + as.getRemoteAET() + " closed before receive of outstanding DIMSE RSP");
			}
			notifyAll();
		}
	}

	public final void setAutoCancel(int autoCancel) {
		this.autoCancel = autoCancel;
	}

	@Override
	public void cancel(Association a) throws IOException {
		if (ex != null)
			throw ex;
		if (!finished)
			super.cancel(a);
	}

	public DicomObject getDataset() {
		return entry.command;
	}

	public DicomObject getCommand() {
		return entry.dataset;
	}

	public MyEntry getEntry() {
		return entry;
	}

	public synchronized boolean next() throws IOException, InterruptedException {
		if (entry.next == null) {
			if (finished)
				return false;

			while (entry.next == null && ex == null)
				wait();

			if (ex != null)
				throw ex;
		}
		entry = entry.next;
		return true;
	}

	public List<DicomObject> getDataList() {
		return dataList;
	}

	public void setDataList(List<DicomObject> dataList) {
		this.dataList = dataList;
	}
}