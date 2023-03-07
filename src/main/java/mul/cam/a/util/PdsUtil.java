package mul.cam.a.util;

import java.util.Date;

public class PdsUtil {
	
	// 파일명 -> 변경(time)
	
	// myfile.txt -> 438565452.txt
	public static String getNewFileName(String filename) {
		String newfilename = "";
		String fpost = "";
		
		if(filename.indexOf('.') >= 0) {	// 확장자명이 있음
			fpost = filename.substring(filename.indexOf('.'));	// .부터 시작해서 끝까지 -> .txt
			newfilename = new Date().getTime() + fpost;	// 438565452 + .txt
		} else {	// 확장자명이 없음
			newfilename = new Date().getTime() + ".back";	// 438565452 + .back
		}
		
		return newfilename;
	}
}
