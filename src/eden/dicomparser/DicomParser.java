package eden.dicomparser;

public class DicomParser {
	
	
	
	//******************************************************
	public static DicomParser getInstance(){
		return Holder.instance;
	}
	private DicomParser(){}
	
	static class Holder{
		public static final DicomParser instance = new DicomParser();
	}
}
