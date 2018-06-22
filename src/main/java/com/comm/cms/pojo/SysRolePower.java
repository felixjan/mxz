package com.comm.cms.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 系统角色权限关联表
 * 
 */
public class SysRolePower implements Serializable {

	 private static final long serialVersionUID = 1L;

	 /**主键id**/
	 private String id;

	 /**角色id**/
	 private String roleId;

	 /**权限id**/
	 private String powerId;

	 /**创建时间**/
	 private Date createTime;


	 public void setId(String id){
		 this.id = id == null ? null : id.trim();
	 }

	 public String getId(){
		 return this.id;
	 }

	 public void setRoleId(String roleId){
		 this.roleId = roleId == null ? null : roleId.trim();
	 }

	 public String getRoleId(){
		 return this.roleId;
	 }

	 public void setPowerId(String powerId){
		 this.powerId = powerId == null ? null : powerId.trim();
	 }

	 public String getPowerId(){
		 return this.powerId;
	 }

	 public void setCreateTime(Date createTime){
		 this.createTime = createTime;
	 }

	 public Date getCreateTime(){
		 return this.createTime;
	 }

}
