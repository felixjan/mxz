package com.comm.cms.mapper;

import com.comm.cms.pojo.SysRolePower;
import java.util.List;
import java.util.Map;

public interface SysRolePowerMapper{

	 /**
	  * 
	  * 删除（根据roleId删除）
	  * 
	  */
	 int delete(String roleId);

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
	 SysRolePower getById(String id);

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
	 List<SysRolePower> getList(Map<String, Object> paramMap);
}