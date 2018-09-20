package kr.itedu.boardmvc.common;

public class Utils {
	public static int getParamInt(String p) {
		int r = -1;
		if(p != null && !p.equals("")) {
			try {
				r = Integer.parseInt(p);
			} catch (Exception e) {}
		}
		return r;
	}
}
