package com.mxz.admin.cms.sys.service.impl;

import com.comm.cms.mapper.SysRolePowerMapper;
import com.comm.cms.pojo.SysRolePower;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.comm.util.Page;
import com.comm.util.ResponseObject;
import com.mxz.admin.cms.sys.service.SysRolePowerService;

@Service
public class SysRolePowerServiceImpl implements SysRolePowerService{

	 @Autowired 
	 private SysRolePowerMapper mapper; 

	 /**
	  * 
	  * 新增数据的方法
	  * 
	  */
	 @SuppressWarnings("unchecked")
	 @Override
	 public ResponseObject create(Map<String, Object> paramMap) {
		 ResponseObject responseObject = new ResponseObject(Boolean.FALSE);
		 if(paramMap==null||paramMap.isEmpty()){
			 responseObject.setResponseMessage("参数不能为空！");
			 return responseObject;
		 }
		 List<String> list = (List<String>) paramMap.get("powerList");
		 String roleId = (String)paramMap.get("roleId");
		 if(list.size()>0&&StringUtils.isNotBlank(roleId)) {
			 responseObject.setResponseStatus(Boolean.TRUE);
			 mapper.delete(roleId);
			 Map<String, Object> map=null;
			 for (String str : list) {
				 map = new HashMap<String, Object>();
				 map.put("powerId", str);
				 map.put("roleId", roleId);
				 mapper.create(map);
			 }
		 }else {
			 responseObject.setResponseMessage("请确认必传参数不能为空！");
		 }
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
	 public SysRolePower getById(String id) {
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
	 public List<SysRolePower> getList(Map<String, Object> paramMap) {
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
		 List<SysRolePower> list = mapper.getList(paramMap);
		 Map<String, Object> responseData = new HashMap<String, Object>();
		 responseData.put("list", list);
		 responseObject.setResponseData(responseData);
		 return responseObject;
	 }

}