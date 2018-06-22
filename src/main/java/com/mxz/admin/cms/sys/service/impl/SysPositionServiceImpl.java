package com.mxz.admin.cms.sys.service.impl;

import com.comm.cms.mapper.SysPositionMapper;
import com.comm.cms.pojo.SysPosition;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.comm.util.Page;
import com.comm.util.ResponseObject;
import com.mxz.admin.cms.sys.service.SysPositionService;

@Service
public class SysPositionServiceImpl implements SysPositionService{

	 @Autowired 
	 private SysPositionMapper mapper; 

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
		 String departmentId=(String)paramMap.get("departmentId");
		 if(StringUtils.isBlank(departmentId)) {
			 responseObject.setResponseMessage("所属部门不能为空！");
			 return responseObject;
		 }
		 String positionName=(String)paramMap.get("positionName");
		 if(StringUtils.isBlank(positionName)) {
			 responseObject.setResponseMessage("职位名称不能为空！");
			 return responseObject;
		 }
		 Map<String, Object> map=new HashMap<String, Object>();
		 map.put("positionName", positionName);
		 map.put("departmentId", departmentId);
		 List<SysPosition> list = mapper.getList(map);
		 if(list.size()>0) {
			 responseObject.setResponseMessage("该部门职位名称已存在！");
			 return responseObject;
		 }
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
		 String departmentId=(String)paramMap.get("departmentId");
		 if(StringUtils.isBlank(departmentId)) {
			 responseObject.setResponseMessage("所属部门不能为空！");
			 return responseObject;
		 }
		 String positionName=(String)paramMap.get("positionName");
		 if(StringUtils.isBlank(positionName)) {
			 responseObject.setResponseMessage("职位名称不能为空！");
			 return responseObject;
		 }
		 Map<String, Object> map=new HashMap<String, Object>();
		 map.put("positionName", positionName);
		 map.put("departmentId", departmentId);
		 List<SysPosition> list = mapper.getList(map);
		 if(list.size()>0&&!list.get(0).getId().equals(id)) {
			 responseObject.setResponseMessage("该部门职位名称已存在！");
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
	 public SysPosition getById(String id) {
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
	 public List<SysPosition> getList(Map<String, Object> paramMap) {
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
		 List<SysPosition> list = mapper.getList(paramMap);
		 Map<String, Object> responseData = new HashMap<String, Object>();
		 responseData.put("list", list);
		 responseObject.setResponseData(responseData);
		 return responseObject;
	 }

}