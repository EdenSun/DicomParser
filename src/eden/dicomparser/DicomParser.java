package eden.dicomparser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.dcm4che2.data.DicomObject;
import org.dcm4che2.data.Tag;
import org.dcm4che2.io.DicomInputStream;

import eden.dicomparser.data.DicomData;
import eden.dicomparser.util.DateHelper;

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
	
	public DicomData read(InputStream in) throws Exception{
		try {
			din = new DicomInputStream(in);
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
		String acquisitionDateStr = dcmObj.getString(Tag.AcquisitionDate); //yyyyMMdd
		String acquisitionTimeStr = dcmObj.getString(Tag.AcquisitionTime);	//094646.358378
		String patientBirthDateStr = dcmObj.getString(Tag.PatientBirthDate);//19711218
		String patientSex = dcmObj.getString(Tag.PatientSex);	//M = male F = female O = other
		String seriesNumber = dcmObj.getString(Tag.SeriesNumber);
		String instanceNumber = dcmObj.getString(Tag.InstanceNumber);
		String modality = dcmObj.getString(Tag.Modality);
		String studyDateStr = dcmObj.getString(Tag.StudyDate);	//20150703
		String bodyPartExamined = dcmObj.getString(Tag.BodyPartExamined);
		String patientWeight = dcmObj.getString(Tag.PatientWeight);
		
		data.setPatientId(patientId);
		data.setPatientName(patientName);
		data.setPatientSex(patientSex);
		data.setPatientPosition(patientPosition);
		data.setSeriesNumber(seriesNumber);
		data.setInstanceNumber(instanceNumber);
		data.setModality(modality);
		data.setBodyPartExamined(bodyPartExamined);
		data.setPatientWeight(patientWeight);
		if( acquisitionDateStr != null ){
			try {
				Date acquisitionDate = DateHelper.parse(acquisitionDateStr,"yyyyMMdd");
				
				data.setAcquisitionDate(acquisitionDate);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if( acquisitionTimeStr != null ){
			try {
				acquisitionTimeStr = acquisitionTimeStr.subSequence(0, acquisitionTimeStr.indexOf(".")).toString();
				
				data.setAcquisitionTime(DateHelper.parse(acquisitionTimeStr,"HHmmss"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		
		if( patientBirthDateStr != null ){
			try {
				Date patientBirthDate = DateHelper.parse(patientBirthDateStr,"yyyyMMdd");
				
				data.setPatientBirthDate(patientBirthDate);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if( studyDateStr != null ){
			try {
				Date studyDate = DateHelper.parse(studyDateStr,"yyyyMMdd");
				
				data.setStudyDate(studyDate);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
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
		File file = new File("D:\\tmp\\DicomFiles\\patient0\\IM0");
		System.out.println(DicomParser.getInstance().read(file));
		
		file = new File("D:\\tmp\\DicomFiles\\patient0\\IM1");
		System.out.println(DicomParser.getInstance().read(file));
	}
	
}
