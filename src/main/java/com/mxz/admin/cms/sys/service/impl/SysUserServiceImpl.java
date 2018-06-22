package com.mxz.admin.cms.sys.service.impl;

import com.comm.cms.mapper.SysPowerMapper;
import com.comm.cms.mapper.SysUserMapper;
import com.comm.cms.pojo.SysPower;
import com.comm.cms.pojo.SysUser;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.comm.util.Md5Utils;
import com.comm.util.Page;
import com.comm.util.ResponseObject;
import com.comm.util.UploadUtils;
import com.comm.util.session.SessionFactory;
import com.mxz.admin.cms.sys.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService{

	 @Autowired 
	 private SysUserMapper mapper; 
	 
	 @Autowired 
	 private SysPowerMapper sysPowerMapper;
	 
	 @Autowired
	 private HttpServletRequest request;
	 
	 @Value("${default.initial.password}")
	 private String initPassword;//初始密码
	 
	 /**
	  * 
	  * 新增数据的方法
	  * 
	  */
	 @Override
	 public ResponseObject create(Map<String, Object> paramMap) {
		 ResponseObject responseObject = new ResponseObject(Boolean.FALSE);
		 if(paramMap==null||paramMap.isEmpty()){
			 responseObject.setResponseMessage("参数不能为空！");
			 return responseObject;
		 }
		 String userName=(String)paramMap.get("userName");
		 if(StringUtils.isBlank(userName)) {
			 responseObject.setResponseMessage("用户名不能为空！");
			 return responseObject;
		 }
		 Map<String, Object> map=new HashMap<String, Object>();
		 map.put("userName", userName);
		 List<SysUser> list = mapper.getList(map);
		 if(list.size()>0) {
			 responseObject.setResponseMessage("该用户名已存在！");
			 return responseObject;
		 }
		 paramMap.put("password", Md5Utils.encrypt3Times(initPassword));//默认初始化密码
		 mapper.create(paramMap);
		 responseObject.setResponseStatus(Boolean.TRUE);
		 return responseObject;
	 }
	 
	 /**
	  * 
	  * 修改数据的方法
	  * 
	  */
	 @Override
	 public ResponseObject update(Map<String, Object> paramMap) {
		 ResponseObject responseObject = new ResponseObject(Boolean.FALSE);
		 if(paramMap==null||paramMap.isEmpty()){
			 responseObject.setResponseMessage("参数不能为空！");
			 return responseObject;
		 }
		 String id=(String)paramMap.get("id");
		 if(StringUtils.isBlank(id)) {
			 responseObject.setResponseMessage("非正常操作，请正常操作数据！");
			 return responseObject;
		 }
		 paramMap.remove("userName");//去掉不能修改的字段 防止数据被修改
		 paramMap.remove("password");//去掉不能修改的字段 防止数据被修改
		 mapper.update(paramMap);
		 responseObject.setResponseStatus(Boolean.TRUE);
		 return responseObject;
	 }

	 /**
	  * 
	  * 根据id获取数据的方法
	  * 
	  */
	 @Override
	 public SysUser getById(String id) {
		 return mapper.getById(id);
	 }

	 /**
	  * 
	  * 获取数据条数的方法
	  * 
	  */
	 @Override
	 public int getCount(Map<String, Object> paramMap) {
		 return mapper.getCount(paramMap);
	 }

	 /**
	  * 
	  * getList的方法
	  * 
	  */
	 @Override
	 public List<SysUser> getList(Map<String, Object> paramMap) {
		 return mapper.getList(paramMap);
	 }

	 /**
	  * 
	  * getPageList的方法
	  * 
	  */
	 @Override
	 public Page getPageList(Map<String, Object> paramMap) {
		 Page page = new Page();
		 page.setData(mapper.getList(paramMap));
		 page.setCount(mapper.getCount(paramMap));
		 return page;
	 }

	 /**
	  * 
	  * getMapList的方法
	  * 
	  */
	 @Override
	 public ResponseObject getMapList(Map<String, Object> paramMap) {
		 ResponseObject responseObject = new ResponseObject(Boolean.TRUE);
		 List<SysUser> list = mapper.getList(paramMap);
		 Map<String, Object> responseData = new HashMap<String, Object>();
		 responseData.put("list", list);
		 responseObject.setResponseData(responseData);
		 return responseObject;
	 }
	 /**
	  * 
	  * 用户修改个人头像
	  * @throws Exception 
	  * 
	  */
	 @Override
	 public ResponseObject updatePic(@RequestParam("file") MultipartFile file,String id) throws Exception {
		 ResponseObject responseObject = new ResponseObject(Boolean.FALSE);
		 if(StringUtils.isBlank(id)) {
			 responseObject.setResponseMessage("非正常操作，请正常操作数据！");
			 return responseObject;
		 }
		 Map<String, Object> map = new HashMap<String, Object>();
		 String uploadUrl = UploadUtils.upload(request, file, "/upload/images/");
		 map.put("id", id);
		 map.put("picUrl", uploadUrl);
		 mapper.update(map);
		 responseObject.setResponseStatus(Boolean.TRUE);
		 return responseObject;
	 }
	 /**
	  * 
	  * 用户修改个人信息方法
	  * 
	  */
	 @Override
	 public ResponseObject updateBaseInfo(Map<String, Object> paramMap) {
		 ResponseObject responseObject = new ResponseObject(Boolean.FALSE);
		 if(paramMap==null||paramMap.isEmpty()){
			 responseObject.setResponseMessage("参数不能为空！");
			 return responseObject;
		 }
		 String id=(String)paramMap.get("id");
		 if(StringUtils.isBlank(id)) {
			 responseObject.setResponseMessage("非正常操作，请正常操作数据！");
			 return responseObject;
		 }
		 paramMap.remove("userName");//去掉不能修改的字段 防止数据被修改
		 paramMap.remove("password");//去掉不能修改的字段 防止数据被修改
		 mapper.update(paramMap);
		 responseObject.setResponseStatus(Boolean.TRUE);
		 return responseObject;
	 }
	 /**
	  * 
	  * 修改用户密码的方法
	  * 
	  */
	 @Override
	 public ResponseObject updatePassword(Map<String, Object> paramMap) {
		 ResponseObject responseObject = new ResponseObject(Boolean.FALSE);
		 if(paramMap==null||paramMap.isEmpty()){
			 responseObject.setResponseMessage("参数不能为空！");
			 return responseObject;
		 }
		 String id=(String)paramMap.get("id");
		 if(StringUtils.isBlank(id)) {
			 responseObject.setResponseMessage("非正常操作，请正常操作数据！");
			 return responseObject;
		 }
		 String password=(String)paramMap.get("password");
		 String newPassword=(String)paramMap.get("newPassword");
		 String newPasswordTwo=(String)paramMap.get("newPasswordTwo");
		 if(StringUtils.isBlank(password)) {
			 responseObject.setResponseMessage("密码为空，请正常操作数据！");
			 return responseObject;
		 }
		 if(StringUtils.isBlank(newPassword)) {
			 responseObject.setResponseMessage("新密码为空，请正常操作数据！");
			 return responseObject;
		 }
		 if(!newPassword.equals(newPasswordTwo)) {
			 responseObject.setResponseMessage("两次密码不一致，请正常操作数据！");
			 return responseObject;
		 }
		 SysUser sysUser = mapper.getById(id);
		 if(sysUser==null) {
			 responseObject.setResponseMessage("该用户不存在，请联系管理员！");
			 return responseObject;
		 }
		 if(sysUser.getIsValid()==1&&sysUser.getStatus()==1) {
			 String password3Times = Md5Utils.encrypt3Times(password);
			 if(!password3Times.equals(sysUser.getPassword())) {
				 responseObject.setResponseMessage("密码有误，请重新输入！");
				 return responseObject;
			 }
			 Map<String, Object> map=new HashMap<String, Object>();
			 map.put("id", id);
			 map.put("password", Md5Utils.encrypt3Times(newPassword));
			 mapper.update(map);
			 responseObject.setResponseStatus(Boolean.TRUE);
			 return responseObject;
		 }else {
			 responseObject.setResponseMessage("不能操作该用户数据，请联系管理员！");
			 return responseObject;
		 }
	 }
	 
	 /**
	  * 获取用户操作权限
	  * @return
	  */
	 @Override
	 public List<SysPower> getPowerList(Map<String, Object> paramMap){
		 SysUser sysUser = SessionFactory.getSessionUser(request);
		 paramMap.put("roleId", sysUser.getRoleId());
		 List<SysPower> list = sysPowerMapper.getList(paramMap);
		 return list;
	 }
	 
	 /**
	  * 获取用户导航权限
	  * @return
	  */
	 @Override
	 public List<Object> getMenuList(){
		 SysUser sysUser = SessionFactory.getSessionUser(request);
		 Map<String, Object> map=new HashMap<String, Object>();
		 map.put("roleId", sysUser.getRoleId());
		 map.put("style", "导航");
		 map.put("limitType", 1);
		 List<SysPower> list = sysPowerMapper.getList(map);
		 return getPowerList(list, null);
	 }
	 
	 /**
	  * 递归循环组装数据
	  * @param list
	  * @return
	  */
	 private List<Object> getPowerList(List<SysPower> list,SysPower power){
		 List<Object> powerList=new ArrayList<Object>();
		 Map<String, Object> map=null;
		 if(power==null) {
			 for (SysPower sysPower : list) {
				 if(sysPower.getLevel()==1) {
					 map=new HashMap<String, Object>();
					 map.put("id", sysPower.getId());
					 map.put("title", sysPower.getLableName());
					 map.put("icon", sysPower.getImageUrl());
					 map.put("url", sysPower.getWebUrl());
					 map.put("children", getPowerList(list, sysPower));
					 powerList.add(map);
				 }
			 }
		 }else {
			 for (SysPower sysPower : list) {
				 if(power.getId().equals(sysPower.getPid())) {
					 map=new HashMap<String, Object>();
					 map.put("id", sysPower.getId());
					 map.put("title", sysPower.getLableName());
					 map.put("icon", sysPower.getImageUrl());
					 map.put("url", sysPower.getWebUrl());
					 map.put("children", getPowerList(list, sysPower));
					 powerList.add(map);
				 }
				 
			 }
		 }
		return powerList;
	 }
}