package eden.dicomparser;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.Tag;
import org.dcm4che2.io.DicomInputStream;

import eden.dicomparser.data.DicomData;

public class DicomParser {
	private Logger logger = Logger.getLogger(this.getClass());
	private DicomObject dcmObj;
	private DicomInputStream din = null;
	
	public DicomData read(File file) throws Exception{
		try {
			din = new DicomInputStream(file);
			dcmObj = din.readDicomObject();
			logger.info("读取Dicom文件完成" + dcmObj);
			
			DicomData data = trans2DicomData(dcmObj);
			return data;
		}
		catch (IOException e) {
			throw new Exception("读取Dicom文件失败");
		}
		finally {
			try {
				din.close();
			}
			catch (IOException ignore) {
			}
		}
	}
	
	private DicomData trans2DicomData(DicomObject dcmObj2) {
		if( dcmObj == null ){
			return null;
		}
		DicomData data = new DicomData();
		String patientName = dcmObj.getString(Tag.PatientName);
		String patientPosition = dcmObj.getString(Tag.PatientPosition);
		String patientId = dcmObj.getString(Tag.PatientID);
		String acquisitionDate = dcmObj.getString(Tag.AcquisitionDate);
		String acquisitionTime = dcmObj.getString(Tag.AcquisitionTime);
		String patientBirthDate = dcmObj.getString(Tag.PatientBirthDate);
		String patientSex = dcmObj.getString(Tag.PatientSex);
		
		data.setAcquisitionDate(acquisitionDate);
		data.setAcquisitionTime(acquisitionTime);
		data.setPatientBirthDate(patientBirthDate);
		data.setPatientId(patientId);
		data.setPatientName(patientName);
		data.setPatientSex(patientSex);
		data.setPatientPosition(patientPosition);
		
		return data;
	}

	//******************************************************
	public static DicomParser getInstance(){
		return Holder.instance;
	}
	private DicomParser(){}
	
	static class Holder{
		public static final DicomParser instance = new DicomParser();
	}
	//*******************************************************
	public static void main(String[] args) throws Exception {
		File file = new File("D:\\tmp\\patient1\\IM58");
		System.out.println(DicomParser.getInstance().read(file));
	}
	
}
