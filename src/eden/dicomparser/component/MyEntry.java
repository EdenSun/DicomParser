package eden.dicomparser.component;

import org.dcm4che2.data.DicomObject;

public class MyEntry {

	final DicomObject command;
	final DicomObject dataset;
	MyEntry next;

	public MyEntry(DicomObject command, DicomObject dataset) {
		this.command = command;
		this.dataset = dataset;
	}

	public DicomObject getCommand() {
		return command;
	}

	public DicomObject getDataset() {
		return dataset;
	}

	public MyEntry getNext() {
		return next;
	}

	public void setNext(MyEntry next) {
		this.next = next;
	}
}