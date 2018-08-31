package library;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class activeString {
	public static String active(String str, String acStr) {
		//chuyển về không dấu
		acStr = removeAccent(acStr);
		String[] strActive = str.split(" ");
		String[] strAccent = new String[strActive.length];
		for( int i = 0; i < strAccent.length; i++) {
			strAccent[i] = removeAccent(strActive[i]);
		}
		
		String result = "";
		for( int i = 0; i < strAccent.length; i++) {
			if( strAccent[i].equalsIgnoreCase(acStr)) {
				strActive[i] = "<span style='color:red'>" + strActive[i] + "</span>" ;
			}
			result += strActive[i] + " ";
		}
		
		return result;
	}
	
	public static String removeAccent(String s) {
		  
		  String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
		  Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		  return pattern.matcher(temp).replaceAll("");
	 }
	
	
}
