package com.mxz.admin.cms.sys.service.impl;

import com.comm.cms.mapper.SysPowerMapper;
import com.comm.cms.mapper.SysRoleMapper;
import com.comm.cms.mapper.SysRolePowerMapper;
import com.comm.cms.pojo.SysPower;
import com.comm.cms.pojo.SysRole;
import com.comm.cms.pojo.SysRolePower;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.comm.util.Page;
import com.comm.util.ResponseObject;
import com.mxz.admin.cms.sys.service.SysRoleService;

@Service
public class SysRoleServiceImpl implements SysRoleService{

	 @Autowired 
	 private SysRoleMapper mapper; 
	 
	 @Autowired 
	 private SysPowerMapper sysPowerMapper;
	 
	 @Autowired 
	 private SysRolePowerMapper sysRolePowerMapper; 

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
		 String roleName=(String)paramMap.get("roleName");
		 if(StringUtils.isBlank(roleName)) {
			 responseObject.setResponseMessage("角色名称不能为空！");
			 return responseObject;
		 }
		 Map<String, Object> map=new HashMap<String, Object>();
		 map.put("roleName", roleName);
		 List<SysRole> list = mapper.getList(map);
		 if(list.size()>0) {
			 responseObject.setResponseMessage("该部门角色名称已存在！");
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
		 String roleName=(String)paramMap.get("roleName");
		 if(StringUtils.isBlank(roleName)) {
			 responseObject.setResponseMessage("角色名称不能为空！");
			 return responseObject;
		 }
		 Map<String, Object> map=new HashMap<String, Object>();
		 map.put("roleName", roleName);
		 List<SysRole> list = mapper.getList(map);
		 if(list.size()>0&&!list.get(0).getId().equals(id)) {
			 responseObject.setResponseMessage("该部门角色名称已存在！");
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
	 public SysRole getById(String id) {
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
	 public List<SysRole> getList(Map<String, Object> paramMap) {
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
		 List<SysRole> list = mapper.getList(paramMap);
		 Map<String, Object> responseData = new HashMap<String, Object>();
		 responseData.put("list", list);
		 responseObject.setResponseData(responseData);
		 return responseObject;
	 }
	 
	 /**
	  * 
	  * 获取角色权限
	  */
	 @Override
	 public ResponseObject getPowerList(Map<String, Object> paramMap) {
		 ResponseObject responseObject = new ResponseObject(Boolean.TRUE);
		 List<SysRolePower> rolePowerList = sysRolePowerMapper.getList(paramMap);
		 paramMap.remove("roleId");
		 List<SysPower> powerList = sysPowerMapper.getList(paramMap);
		 Map<String, Object> responseData = new HashMap<String, Object>();
		 responseData.put("powerList", getPowerList(powerList,null,rolePowerList));
		 responseObject.setResponseData(responseData);
		 return responseObject;
	 }
	 
	 /**
	  * 递归循环组装数据
	  * @param list
	  * @return
	  */
	 private List<Object> getPowerList(List<SysPower> list,SysPower power,List<SysRolePower> rolePowerList){
		 List<Object> powerList=new ArrayList<Object>();
		 Map<String, Object> map=null;
		 if(power==null) {
			 for (SysPower sysPower : list) {
				 if(sysPower.getLevel()==1&&sysPower.getIsFinal()==0) {
					 map=new HashMap<String, Object>();
					 map.put("title", sysPower.getLableName());
					 map.put("value", sysPower.getId());
					 map.put("data", getPowerList(list,sysPower,rolePowerList));
					 for (SysRolePower sysRolePower : rolePowerList) {
						 if(sysPower.getId().equals(sysRolePower.getPowerId())) {
							 map.put("checked", true);
						 }
					 }
					 powerList.add(map);
				 }else if(sysPower.getLevel()==1&&sysPower.getIsFinal()==1){
					 map=new HashMap<String, Object>();
					 map.put("title", sysPower.getLableName());
					 map.put("value", sysPower.getId());
					 map.put("data", new ArrayList<Object>());
					 for (SysRolePower sysRolePower : rolePowerList) {
						 if(sysPower.getId().equals(sysRolePower.getPowerId())) {
							 map.put("checked", true);
						 }
					 }
					 powerList.add(map);
				 }
			 }
		 }else {
			 for (SysPower sysPower : list) {
				 if(sysPower.getIsFinal()==0&&power.getId().equals(sysPower.getPid())) {
					 map=new HashMap<String, Object>();
					 map.put("title", sysPower.getLableName());
					 map.put("value", sysPower.getId());
					 map.put("data", getPowerList(list,sysPower,rolePowerList));
					 for (SysRolePower sysRolePower : rolePowerList) {
						 if(sysPower.getId().equals(sysRolePower.getPowerId())) {
							 map.put("checked", true);
						 }
					 }
					 powerList.add(map);
				 }else if(sysPower.getIsFinal()==1&&power.getId().equals(sysPower.getPid())){
					 map=new HashMap<String, Object>();
					 map.put("title", sysPower.getLableName());
					 map.put("value", sysPower.getId());
					 map.put("data", new ArrayList<Object>());
					 for (SysRolePower sysRolePower : rolePowerList) {
						 if(sysPower.getId().equals(sysRolePower.getPowerId())) {
							 map.put("checked", true);
						 }
					 }
					 powerList.add(map);
				 }
				 
			 }
		 }
		return powerList;
	 }

}