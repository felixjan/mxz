package com.comm.util.session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.comm.cms.pojo.SysUser;
import com.comm.util.session.SysConstant;

/**
 * 获取Session中数据
 */
public class SessionFactory{
	/**
	 * 后台获取登陆会员
	 * @return
	 */
	public static SysUser getSessionUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute(SysConstant.SESSION_USER) != null) {
			return (SysUser) session.getAttribute(SysConstant.SESSION_USER);
		} 
		return null;
	}
	/**
	 * 后台获取登陆会员权限
	 * @return
	 */
	public static String getSessionUserPower(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute(SysConstant.SESSION_USERPOWER) != null) {
			return (String) session.getAttribute(SysConstant.SESSION_USERPOWER);
		} 
		return "";
	}
}
