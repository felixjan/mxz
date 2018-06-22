package com.comm.cms.mapper;

import com.comm.cms.pojo.CmsCity;
import java.util.List;
import java.util.Map;

public interface CmsCityMapper{

	 /**
	  * 
	  * 删除（根据主键ID删除）
	  * 
	  */
	 int delete(int id);

	 /**
	  * 
	  * 添加
	  * 
	  */
	 int create(Map<String, Object> paramMap);

	 /**
	  * 
	  * 修改 （匹配有值的字段）
	  * 
	  */
	 int update(Map<String, Object> paramMap);

	 /**
	  * 
	  * 查询（根据主键ID查询）
	  * 
	  */
	 CmsCity getById(int id);

	 /**
	  * 
	  * 获取数据条数的方法
	  * 
	  */
	 int getCount(Map<String, Object> paramMap);

	 /**
	  * 
	  * getList的方法
	  * 
	  */
	 List<CmsCity> getList(Map<String, Object> paramMap);
}