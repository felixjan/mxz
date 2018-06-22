package com.mxz.admin.cms.dict.service;

import com.comm.cms.pojo.CmsCity;
import java.util.List;
import java.util.Map;
import com.comm.util.Page;
import com.comm.util.ResponseObject;

public interface CmsCityService{

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
	 public CmsCity getById(int id);

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
	 public List<CmsCity> getList(Map<String, Object> paramMap);

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

}