package eden.dicomparser.data;

public class DicomData {
	private String patientId;
	private String patientName;
	private String patientBirthDate;
	private String patientSex;
	private String acquisitionDate;
	private String acquisitionTime;
	private String patientPosition;
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
	public String getPatientBirthDate() {
		return patientBirthDate;
	}
	public void setPatientBirthDate(String patientBirthDate) {
		this.patientBirthDate = patientBirthDate;
	}
	public String getPatientSex() {
		return patientSex;
	}
	public void setPatientSex(String patientSex) {
		this.patientSex = patientSex;
	}
	public String getAcquisitionDate() {
		return acquisitionDate;
	}
	public void setAcquisitionDate(String acquisitionDate) {
		this.acquisitionDate = acquisitionDate;
	}
	public String getAcquisitionTime() {
		return acquisitionTime;
	}
	public void setAcquisitionTime(String acquisitionTime) {
		this.acquisitionTime = acquisitionTime;
	}
	public String getPatientPosition() {
		return patientPosition;
	}
	public void setPatientPosition(String patientPosition) {
		this.patientPosition = patientPosition;
	}
	@Override
	public String toString() {
		return "DicomData [patientId=" + patientId + ", patientName=" + patientName + ", patientBirthDate="
				+ patientBirthDate + ", patientSex=" + patientSex + ", acquisitionDate=" + acquisitionDate
				+ ", acquisitionTime=" + acquisitionTime + ", patientPosition=" + patientPosition + "]";
	}
	
}
