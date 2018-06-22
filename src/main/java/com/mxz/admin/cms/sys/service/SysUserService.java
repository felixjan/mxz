package com.mxz.admin.cms.sys.service;

import com.comm.cms.pojo.SysPower;
import com.comm.cms.pojo.SysUser;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.comm.util.Page;
import com.comm.util.ResponseObject;

public interface SysUserService{

	 /**
	  * 
	  * 新增数据的方法
	  * 
	  */
	 public ResponseObject create(Map<String, Object> paramMap); 

	 /**
	  * 
	  * 修改对象的方法
	  * 
	  */
	 public ResponseObject update(Map<String, Object> paramMap); 

	 /**
	  * 
	  * 根据id获取对象的方法
	  * 
	  */
	 public SysUser getById(String id);

	 /**
	  * 
	  * 获取数据条数的方法
	  * 
	  */
	 public int getCount(Map<String, Object> paramMap);

	 /**
	  * 
	  * getList的方法
	  * 
	  */
	 public List<SysUser> getList(Map<String, Object> paramMap);

	 /**
	  * 
	  * getPageList的方法
	  * 
	  */
	 public Page getPageList(Map<String, Object> paramMap);

	 /**
	  * 
	  * getMapList的方法
	  * 
	  */
	 public ResponseObject getMapList(Map<String, Object> paramMap);
	 
	 /**
	  * 
	  * 用户修改个人头像方法
	 * @throws Exception 
	  * 
	  */
	 ResponseObject updatePic(MultipartFile file, String id) throws Exception;
	 
	 /**
	  * 
	  * 用户修改个人信息方法
	  * 
	  */
	 ResponseObject updateBaseInfo(Map<String, Object> paramMap);
	 
	 /**
	  * 
	  * 修改用户密码的方法
	  * 
	  */
	 ResponseObject updatePassword(Map<String, Object> paramMap);
	 
	 /**
	  * 获取用户操作权限
	  * 
	  */
	 List<SysPower> getPowerList(Map<String, Object> paramMap);

	 /**
	  * 获取用户导航权限
	  * 
	  */
	 List<Object> getMenuList();

}