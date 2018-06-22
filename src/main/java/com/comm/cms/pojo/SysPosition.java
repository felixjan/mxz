package com.comm.cms.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 系统职位表
 * 
 */
public class SysPosition implements Serializable {

	 private static final long serialVersionUID = 1L;

	 /**主键id**/
	 private String id;

	 /**职位名称**/
	 private String positionName;

	 /**部门id**/
	 private String departmentId;

	 /**部门名称**/
	 private String departmentName;

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

	 public void setPositionName(String positionName){
		 this.positionName = positionName == null ? null : positionName.trim();
	 }

	 public String getPositionName(){
		 return this.positionName;
	 }

	 public void setDepartmentId(String departmentId){
		 this.departmentId = departmentId == null ? null : departmentId.trim();
	 }

	 public String getDepartmentId(){
		 return this.departmentId;
	 }

	 public void setDepartmentName(String departmentName){
		 this.departmentName = departmentName == null ? null : departmentName.trim();
	 }

	 public String getDepartmentName(){
		 return this.departmentName;
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
