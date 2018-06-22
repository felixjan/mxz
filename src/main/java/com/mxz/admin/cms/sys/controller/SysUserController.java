package com.mxz.admin.cms.sys.controller;

import com.comm.cms.pojo.SysPower;
import com.comm.cms.pojo.SysUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.comm.util.MapUtils;
import com.comm.util.Page;
import com.comm.util.ResponseObject;
import com.comm.util.session.SessionFactory;
import com.comm.util.session.SysConstant;
import com.mxz.admin.cms.sys.service.SysUserService;

@Controller
@RequestMapping("/sys/user")
public class SysUserController {

	 @Autowired 
	 private SysUserService service; 

	 /**
	  * 
	  * 新增数据的接口
	  * 
	  */
	 @ResponseBody
	 @RequestMapping(value="/create",method=RequestMethod.POST) 
	 public ResponseObject create(@RequestBody(required=false) Map<String,Object> paramMap) {
		 return service.create(paramMap);
	 }

	 /**
	  * 
	  * 修改数据的接口
	  * 
	  */
	 @ResponseBody
	 @RequestMapping(value="/update",method=RequestMethod.POST) 
	 public ResponseObject update(@RequestBody(required=false) Map<String,Object> paramMap) {
		 return service.update(paramMap);
	 }

	 /**
	  * 
	  * 根据id获取数据的接口
	  * 
	  */
	 @ResponseBody
	 @RequestMapping(value="/getById/{id}") 
	 public SysUser getById(@PathVariable String id) {
		 return service.getById(id);
	 }

	 /**
	  * 
	  * getList的接口
	  * 
	  */
	 @ResponseBody
	 @RequestMapping(value="/getList") 
	 public List<SysUser> getList(@RequestBody(required=false) Map<String,Object> paramMap) {
		 return service.getList(paramMap);
	 }

	 /**
	  * 
	  * getPageList的接口
	  * 
	  */
	 @ResponseBody
	 @RequestMapping(value="/getPageList",method=RequestMethod.POST) 
	 public Page getPageList(String queryJson,int page,int limit) {
		 return service.getPageList(MapUtils.JsonToMap(queryJson, page, limit));
	 }

	 /**
	  * 
	  * getMapList的接口
	  * 
	  */
	 @ResponseBody
	 @RequestMapping(value="/getMapList") 
	 public ResponseObject getMapList(@RequestBody(required=false) Map<String,Object> paramMap) {
		 return service.getMapList(paramMap);
	 }
	 
	 /**
	  * 
	  * 用户修改个人信息
	  * 
	  */
	 @ResponseBody
	 @RequestMapping(value="/updateBaseInfo",method=RequestMethod.POST) 
	 public ResponseObject updateBaseInfo(@RequestBody(required=false) Map<String,Object> paramMap) {
		 return service.updateBaseInfo(paramMap);
	 }
	 
	 /**
	  * 
	  * 修改用户密码
	  * 
	  */
	 @ResponseBody
	 @RequestMapping(value="/updatePassword",method=RequestMethod.POST) 
	 public ResponseObject updatePassword(@RequestBody(required=false) Map<String,Object> paramMap) {
		 return service.updatePassword(paramMap);
	 }
	 
	 /**
	  * 判断用户是否登录
	  * @param paramMap
	  * @return
	  */
	 @ResponseBody
	 @RequestMapping(value="/getIsLogin") 
	 public ResponseObject getIsLogin(HttpServletRequest request) {
		 ResponseObject responseObject = new ResponseObject(Boolean.TRUE);
		 SysUser sysUser = SessionFactory.getSessionUser(request);
		 Map<String,Object> map = new HashMap<String, Object>();
		 SysUser sysUserNow = service.getById(sysUser.getId());
		 request.getSession().setAttribute(SysConstant.SESSION_USER, sysUserNow);
		 map.put("sysUser", sysUserNow);
		 responseObject.setResponseData(map);
		 return responseObject;
	 }
	 
	 /**
	  * 获取用户操作权限数据
	  */
	 @ResponseBody
	 @RequestMapping(value="/getPowerList") 
	 public List<SysPower> getPowerList(@RequestBody(required=false) Map<String,Object> paramMap) {
		 return service.getPowerList(paramMap);
	 }
	 
	 /**
	  * 获取导航菜单数据
	  */
	 @ResponseBody
	 @RequestMapping(value="/getMenuList") 
	 public List<Object> getMenuList() {
		 return service.getMenuList();
	 }
	 
	/**
	 * 上传文件会自动绑定到MultipartFile中 前后端分离文件上传
	 * @param request
	 * @param file
	 * @return
	 * @throws Exception
	 */
    @ResponseBody
    @RequestMapping(value="/updatePic",method=RequestMethod.POST)
    public ResponseObject updatePic(@RequestParam("file") MultipartFile file,String id) throws Exception {
    	return service.updatePic(file, id);
    }
}