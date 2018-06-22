package com.mxz.admin.cms.sys.service;

import com.comm.cms.pojo.SysPosition;
import java.util.List;
import java.util.Map;
import com.comm.util.Page;
import com.comm.util.ResponseObject;

public interface SysPositionService{

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
	 public SysPosition getById(String id);

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
	 public List<SysPosition> getList(Map<String, Object> paramMap);

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