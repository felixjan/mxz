package com.mxz.admin.cms.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.comm.cms.pojo.SysPower;
import com.comm.cms.pojo.SysUser;
import com.comm.util.Md5Utils;
import com.comm.util.ResponseObject;
import com.comm.util.session.SysConstant;
import com.mxz.admin.cms.sys.service.SysPowerService;
import com.mxz.admin.cms.sys.service.SysUserService;
import com.comm.util.VerifyCodeUtils;

@Controller
@RequestMapping("/login")
public class LoginController {
	private static final Logger logger = LogManager.getLogger(LoginController.class);
	@Autowired 
	private SysUserService service;
	@Autowired 
	private SysPowerService sysPowerService;
	@Autowired
	private HttpServletRequest request;
	
	@ResponseBody
    @RequestMapping("/tologin")  
    public ResponseObject tologin(@RequestBody(required=false) Map<String,Object> paramMap) throws Exception{
		ResponseObject responseObject = new ResponseObject(Boolean.FALSE);
		if(paramMap==null||paramMap.isEmpty()){
			return responseObject;
		}
		HttpSession session = request.getSession(); 
		String verifyCode= String.valueOf(paramMap.get("verifyCode"));
		if(StringUtils.isNotBlank(verifyCode)) {
			Object seesionCode = session.getAttribute("verifyCode");
			if(seesionCode == null) {
				responseObject.setResponseMessage("验证码已过期，请点击重新获取！");
    			return responseObject;
			}
			if(!seesionCode.toString().equalsIgnoreCase(verifyCode)) {
				responseObject.setResponseMessage("验证码输入有误，请重新输入！");
    			return responseObject;
			}
		}else {
			responseObject.setResponseMessage("验证码不能为空！");
			return responseObject;
		}
		String userName = String.valueOf(paramMap.get("userName"));
		if(StringUtils.isBlank(userName)) {
			responseObject.setResponseMessage("用户名不能为空！");
			return responseObject;
		}
		String pwd=String.valueOf(paramMap.get("password"));
		if(StringUtils.isBlank(pwd)) {
			responseObject.setResponseMessage("密码不能为空！");
			return responseObject;
		}
		String password = Md5Utils.encrypt3Times(pwd);
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("userName", userName);
		map.put("password", password);
		List<SysUser> list = service.getList(map);
		if(list.size()>0){
			SysUser sysUser = list.get(0);
			if(sysUser.getStatus()==2) {
				responseObject.setResponseMessage("该用户已离职，请联系管理员！");
				return responseObject;
			}else if(sysUser.getStatus()==3) {
				responseObject.setResponseMessage("该用户已被冻结，请联系管理员！");
				return responseObject;
			}
			request.getSession().setAttribute(SysConstant.SESSION_USER,sysUser);
			Map<String,Object> mapPower=new HashMap<String, Object>();
			mapPower.put("roleId", sysUser.getRoleId());
			mapPower.put("limitType", 1);
			List<SysPower> listPower = sysPowerService.getList(mapPower);
			if(listPower.size()>0) {
				StringBuffer actionStr=new StringBuffer();
				for (SysPower sysPower : listPower) {
					if(sysPower.getStyle().equals("权限")) {
						String actionUrl=sysPower.getActionUrl();
						if(StringUtils.isNotBlank(actionUrl)) {
							actionStr.append(actionUrl);
							actionStr.append("|");
						}
					}
				}
				session.setAttribute(SysConstant.SESSION_USERPOWER,actionStr.toString());
			}else {
				session.setAttribute(SysConstant.SESSION_USERPOWER,"");
			}
			logger.info("用户登录成功！");
			responseObject.setResponseStatus(Boolean.TRUE);
		}else {
			responseObject.setResponseMessage("用户名或密码错误，请重新登录！");
		}
        return responseObject;  
    } 
	
	@ResponseBody
    @RequestMapping("/exit") 
    public ResponseObject exit(@RequestBody(required=false) Map<String,Object> paramMap) throws Exception{
		ResponseObject responseObject = new ResponseObject(Boolean.TRUE);
		request.getSession().removeAttribute(SysConstant.SESSION_USER);
		request.getSession().removeAttribute(SysConstant.SESSION_USERPOWER);
		return responseObject;
	}
	
	/**
     * 获取验证码
     * @param paramMap
     * @param request
     * @param response
     * @return
     * @throws IOException 
     */
    @ResponseBody
	@RequestMapping("/getVerifyCode") 
    public void getVerifyCode(HttpServletResponse response) throws Exception{
    	HttpSession session = request.getSession(); 
	    session.removeAttribute("verifyCode");
    	//设置不缓存图片    
        response.setHeader("Pragma", "No-cache");    
        response.setHeader("Cache-Control", "No-cache");    
        response.setDateHeader("Expires", 0) ;    
        //指定生成的相应图片    
        response.setContentType("image/jpeg") ;    
        String verifyCode =VerifyCodeUtils.generateVerifyCode(4);
        session.setAttribute("verifyCode", verifyCode);
        VerifyCodeUtils.outputImage(200, 80, response.getOutputStream(), verifyCode);
    }
}
