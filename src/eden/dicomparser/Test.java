package eden.dicomparser;

import java.awt.image.BufferedImage;
import java.util.List;

import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.Tag;
import org.dcm4che2.tool.dcmqr.DcmQR;

public class Test {
	public static void main(String[] args) {
		DcmQR dcmqr = new DcmQR("server");

		dcmqr.setCalledAET("server", true);
		dcmqr.setRemoteHost("213.165.94.158");
		dcmqr.setRemotePort(104);
		dcmqr.getKeys();

		dcmqr.setDateTimeMatching(true);
		dcmqr.setCFind(true);
		dcmqr.setCGet(true);

		dcmqr.setQueryLevel(DcmQR.QueryRetrieveLevel.IMAGE);

		dcmqr.addMatchingKey(Tag.toTagPath("PatientID"), "2011");
		dcmqr.addMatchingKey(Tag.toTagPath("StudyInstanceUID"),
				"1.2.276.0.7230010.3.1.2.669896852.2528.1325171276.917");
		dcmqr.addMatchingKey(Tag.toTagPath("SeriesInstanceUID"),
				"1.2.276.0.7230010.3.1.3.669896852.2528.1325171276.916");

		dcmqr.configureTransferCapability(true);
		List<DicomObject> result = null;
		byte[] imgTab = null;
		BufferedImage bImage = null;
		try {
			dcmqr.start();
			System.out.println("started");
			dcmqr.open();
			System.out.println("opened");
			result = dcmqr.query();
			System.out.println("queried");
			dcmqr.get(result);
			System.out.println("List Size = " + result.size());

			for (DicomObject dco : result) {
				System.out.println(dco);
				//dcmTools.toByteArray(dco);
				System.out.println("end parsing");
			}

		} catch (Exception e) {
			System.out.println("error " + e);
		}

		try {
			dcmqr.stop();
			dcmqr.close();
		} catch (Exception e) {

		}

		System.out.println("done");
	}
}
