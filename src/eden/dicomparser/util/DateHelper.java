package eden.dicomparser.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {

	public static Date parse(String dateTimeStr, String format) throws ParseException {
		DateFormat df = new SimpleDateFormat(format);
		return df.parse(dateTimeStr);
	}

}
