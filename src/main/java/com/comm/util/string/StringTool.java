package com.comm.util.string;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTool {
	/**
	 * 判断手机号格式
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobile(String mobiles) {
		Pattern p = Pattern.compile("^((1))\\d{10}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}
	/**
	 * 判断邮箱格式
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		Pattern pattern = Pattern.compile("[\\w[.-]]+@[\\w[.-]]+\\.[\\w]+");
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}
	/**
	 * 处理map中的字符串
	 * @param paramMap
	 * @param mapStr
	 * @return
	 */
	public static String getMapString(Map<String, Object> paramMap, String mapStr) {
		String returnString = (paramMap!=null && paramMap.get(mapStr) != null) ? paramMap.get(mapStr).toString() : "";
		return returnString;
	}


	public static void main(String[] args) {
		System.out.println(StringTool.isEmail("zybyjy@sohu.com"));
		System.out.println(StringTool.isMobile("1333011111111"));
	}


}
