package eden.dicomparser;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.dcm4che2.data.DicomElement;
import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.Tag;
import org.dcm4che2.imageio.plugins.dcm.DicomImageReadParam;
import org.dcm4che2.io.DicomOutputStream;
import org.dcm4che2.tool.dcmqr.DcmQR;

public class Test {
	public static void main(String[] args) {
		DcmQR dcmqr = new DcmQR("server");

		dcmqr.setCalledAET("server", true);
		dcmqr.setRemoteHost("213.165.94.158");
		dcmqr.setRemotePort(11112);
		dcmqr.getKeys();

		dcmqr.setDateTimeMatching(true);
		dcmqr.setCFind(true);
		dcmqr.setCGet(true);
		dcmqr.configureTransferCapability(true);

		dcmqr.setQueryLevel(DcmQR.QueryRetrieveLevel.IMAGE);
//		dcmqr.setQueryLevel(DcmQR.QueryRetrieveLevel.PATIENT);
//		dcmqr.setQueryLevel(DcmQR.QueryRetrieveLevel.STUDY);
		
		dcmqr.addMatchingKey(Tag.toTagPath("PatientID"), "PAT023");
		/*dcmqr.addMatchingKey(Tag.toTagPath("StudyDate"),
				"19971118");*/
		/*dcmqr.addMatchingKey(Tag.toTagPath("StudyInstanceUID"),
				"1.2.826.0.1.3680043.11.106");*/
		/*dcmqr.addMatchingKey(Tag.toTagPath("PatientID"), "2011");
		dcmqr.addMatchingKey(Tag.toTagPath("StudyInstanceUID"),
				"1.2.276.0.7230010.3.1.2.669896852.2528.1325171276.917");
		dcmqr.addMatchingKey(Tag.toTagPath("SeriesInstanceUID"),
				"1.2.276.0.7230010.3.1.3.669896852.2528.1325171276.916");
		dcmqr.addMatchingKey(Tag.toTagPath("ExamDateAndTime"), "Sep 18 1997 1:00PM");*/
		
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
				//System.out.println(dco);
				
				for( Iterator<DicomElement> it = dco.datasetIterator(); it.hasNext();){
					DicomElement ele = it.next();
					System.out.println(ele);
				}
				
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
	
	/*public static byte[] toByteArray(DicomObject obj) throws IOException {
		ByteArrayInputStream bais = new ByteArrayInputStream();
		BufferedInputStream bos = new BufferedInputStream(bais);
		DicomInputStream dis = new DicomInputStream(obj)

		dis.readDicomObject();
		
		dis.close();
		byte[] data = baos.toByteArray();
		return data;
	}*/
	
	public static byte[] toByteArray(DicomObject obj) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(baos);
		DicomOutputStream dos = new DicomOutputStream(bos);
		dos.writeDicomFile(obj);
		
		dos.close();
		byte[] data = baos.toByteArray();
		return data;
	}
	
	public static BufferedImage getPixelDataAsBufferedImage(byte[] dicomData) throws IOException {
		ByteArrayInputStream bais = new ByteArrayInputStream(dicomData);
		BufferedImage buff = null;
		Iterator<ImageReader> iter = ImageIO.getImageReadersByFormatName("DICOM");
		ImageReader reader = (ImageReader) iter.next();
		DicomImageReadParam param = (DicomImageReadParam) reader.getDefaultReadParam();
		ImageInputStream iis = ImageIO.createImageInputStream(bais);
		reader.setInput(iis, false);
		buff = reader.read(0, param);
		iis.close();
		if (buff == null)
			throw new IOException("Could not read Dicom file. Maybe pixel data is invalid.");
		return buff;
	}

	/*
	public static byte[] toByteArray(DicomObject obj) throws IOException {
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    BufferedOutputStream bos = new BufferedOutputStream(baos);
	    DicomOutputStream dos = new DicomOutputStream(bos);
	    dos.writeDicomFile(obj);
	    dos.close();
	    byte[] data = baos.toByteArray();
	    return data;
	}*/
}
