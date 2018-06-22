package com.comm.cms.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 系统角色表
 * 
 */
public class SysRole implements Serializable {

	 private static final long serialVersionUID = 1L;

	 /**主键id**/
	 private String id;

	 /**角色名称**/
	 private String roleName;

	 /**描述**/
	 private String description;

	 /**修改时间**/
	 private Date updateTime;

	 /**创建时间**/
	 private Date createTime;

	 /**是否有效:0.无效1.有效**/
	 private Integer isValid;


	 public void setId(String id){
		 this.id = id == null ? null : id.trim();
	 }

	 public String getId(){
		 return this.id;
	 }

	 public void setRoleName(String roleName){
		 this.roleName = roleName == null ? null : roleName.trim();
	 }

	 public String getRoleName(){
		 return this.roleName;
	 }

	 public void setDescription(String description){
		 this.description = description == null ? null : description.trim();
	 }

	 public String getDescription(){
		 return this.description;
	 }

	 public void setUpdateTime(Date updateTime){
		 this.updateTime = updateTime;
	 }

	 public Date getUpdateTime(){
		 return this.updateTime;
	 }

	 public void setCreateTime(Date createTime){
		 this.createTime = createTime;
	 }

	 public Date getCreateTime(){
		 return this.createTime;
	 }

	 public void setIsValid(Integer isValid){
		 this.isValid = isValid;
	 }

	 public Integer getIsValid(){
		 return this.isValid;
	 }

}
