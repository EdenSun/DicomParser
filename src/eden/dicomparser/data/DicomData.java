package eden.dicomparser.data;

import java.util.Date;

public class DicomData {
	private String patientId;
	private String patientName;
	private Date patientBirthDate;
	private String patientSex;
	private Date acquisitionDate;
	private Date acquisitionTime;
	private String patientPosition;
	//序列号:识别不同检查的号码
	private String seriesNumber ;
	//图像码:辨识图像的号码
	private String instanceNumber ;
	//检查模态(MRI/CT/CR/DR)
	private String modality ;
	//检查日期
	private Date studyDate ;
	//检查的身体部位
	private String bodyPartExamined ;
	private String patientWeight ;
	private String studyId;
	private String studyDescription;
	
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public Date getPatientBirthDate() {
		return patientBirthDate;
	}
	public void setPatientBirthDate(Date patientBirthDate) {
		this.patientBirthDate = patientBirthDate;
	}
	public String getPatientSex() {
		return patientSex;
	}
	public void setPatientSex(String patientSex) {
		this.patientSex = patientSex;
	}
	public Date getAcquisitionDate() {
		return acquisitionDate;
	}
	public void setAcquisitionDate(Date acquisitionDate) {
		this.acquisitionDate = acquisitionDate;
	}
	public Date getAcquisitionTime() {
		return acquisitionTime;
	}
	public void setAcquisitionTime(Date acquisitionTime) {
		this.acquisitionTime = acquisitionTime;
	}
	public String getPatientPosition() {
		return patientPosition;
	}
	public void setPatientPosition(String patientPosition) {
		this.patientPosition = patientPosition;
	}
	public String getSeriesNumber() {
		return seriesNumber;
	}
	public void setSeriesNumber(String seriesNumber) {
		this.seriesNumber = seriesNumber;
	}
	public String getInstanceNumber() {
		return instanceNumber;
	}
	public void setInstanceNumber(String instanceNumber) {
		this.instanceNumber = instanceNumber;
	}
	public String getModality() {
		return modality;
	}
	public void setModality(String modality) {
		this.modality = modality;
	}
	public Date getStudyDate() {
		return studyDate;
	}
	public void setStudyDate(Date studyDate) {
		this.studyDate = studyDate;
	}
	public String getBodyPartExamined() {
		return bodyPartExamined;
	}
	public void setBodyPartExamined(String bodyPartExamined) {
		this.bodyPartExamined = bodyPartExamined;
	}
	public String getPatientWeight() {
		return patientWeight;
	}
	public void setPatientWeight(String patientWeight) {
		this.patientWeight = patientWeight;
	}
	public String getStudyId() {
		return studyId;
	}
	public void setStudyId(String studyId) {
		this.studyId = studyId;
	}
	public String getStudyDescription() {
		return studyDescription;
	}
	public void setStudyDescription(String studyDescription) {
		this.studyDescription = studyDescription;
	}
	@Override
	public String toString() {
		return "DicomData [patientId=" + patientId + ", patientName="
				+ patientName + ", patientBirthDate=" + patientBirthDate
				+ ", patientSex=" + patientSex + ", acquisitionDate="
				+ acquisitionDate + ", acquisitionTime=" + acquisitionTime
				+ ", patientPosition=" + patientPosition + ", seriesNumber="
				+ seriesNumber + ", instanceNumber=" + instanceNumber
				+ ", modality=" + modality + ", studyDate=" + studyDate
				+ ", bodyPartExamined=" + bodyPartExamined + ", patientWeight="
				+ patientWeight + ", studyId=" + studyId
				+ ", studyDescription=" + studyDescription + "]";
	}
}
