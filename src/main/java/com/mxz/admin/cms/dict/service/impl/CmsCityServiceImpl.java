package com.mxz.admin.cms.dict.service.impl;

import com.comm.cms.mapper.CmsCityMapper;
import com.comm.cms.pojo.CmsCity;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.comm.util.Page;
import com.comm.util.ResponseObject;
import com.mxz.admin.cms.dict.service.CmsCityService;

@Service
public class CmsCityServiceImpl implements CmsCityService{

	 @Autowired 
	 private CmsCityMapper mapper; 

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
	 public CmsCity getById(int id) {
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
	 public List<CmsCity> getList(Map<String, Object> paramMap) {
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
		 List<CmsCity> list = mapper.getList(paramMap);
		 Map<String, Object> responseData = new HashMap<String, Object>();
		 responseData.put("list", list);
		 responseObject.setResponseData(responseData);
		 return responseObject;
	 }

}