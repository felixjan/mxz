package com.comm.cms.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 系统用户表
 * 
 */
public class SysUser implements Serializable {

	 private static final long serialVersionUID = 1L;

	 /**主键**/
	 private String id;

	 /**用户名**/
	 private String userName;

	 /**密码**/
	 private String password;

	 /**姓名**/
	 private String realName;

	 /**部门id**/
	 private String departmentId;

	 /**部门名称**/
	 private String departmentName;

	 /**职位id**/
	 private String positionId;

	 /**职位名称**/
	 private String positionName;

	 /**关联角色id**/
	 private String roleId;

	 /**角色名称**/
	 private String roleName;

	 /**性别:1.男|2.女**/
	 private Integer sex;

	 /**手机号**/
	 private String phone;

	 /**邮箱**/
	 private String email;

	 /**qq号码**/
	 private String qq;

	 /**微信号**/
	 private String weiXin;

	 /**生日**/
	 private Date birthday;

	 /**用户照片**/
	 private String picUrl;

	 /**是否是初始密码1.是2.不是**/
	 private Integer isInitPwd;

	 /**状态:1.在职2.离职3.冻结**/
	 private Integer status;

	 /**入职时间**/
	 private Date joinTime;

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

	 public void setUserName(String userName){
		 this.userName = userName == null ? null : userName.trim();
	 }

	 public String getUserName(){
		 return this.userName;
	 }

	 public void setPassword(String password){
		 this.password = password == null ? null : password.trim();
	 }

	 public String getPassword(){
		 return this.password;
	 }

	 public void setRealName(String realName){
		 this.realName = realName == null ? null : realName.trim();
	 }

	 public String getRealName(){
		 return this.realName;
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

	 public void setPositionId(String positionId){
		 this.positionId = positionId == null ? null : positionId.trim();
	 }

	 public String getPositionId(){
		 return this.positionId;
	 }

	 public void setPositionName(String positionName){
		 this.positionName = positionName == null ? null : positionName.trim();
	 }

	 public String getPositionName(){
		 return this.positionName;
	 }

	 public void setRoleId(String roleId){
		 this.roleId = roleId == null ? null : roleId.trim();
	 }

	 public String getRoleId(){
		 return this.roleId;
	 }

	 public void setRoleName(String roleName){
		 this.roleName = roleName == null ? null : roleName.trim();
	 }

	 public String getRoleName(){
		 return this.roleName;
	 }

	 public void setSex(Integer sex){
		 this.sex = sex;
	 }

	 public Integer getSex(){
		 return this.sex;
	 }

	 public void setPhone(String phone){
		 this.phone = phone == null ? null : phone.trim();
	 }

	 public String getPhone(){
		 return this.phone;
	 }

	 public void setEmail(String email){
		 this.email = email == null ? null : email.trim();
	 }

	 public String getEmail(){
		 return this.email;
	 }

	 public void setQq(String qq){
		 this.qq = qq == null ? null : qq.trim();
	 }

	 public String getQq(){
		 return this.qq;
	 }

	 public void setWeiXin(String weiXin){
		 this.weiXin = weiXin == null ? null : weiXin.trim();
	 }

	 public String getWeiXin(){
		 return this.weiXin;
	 }

	 public void setBirthday(Date birthday){
		 this.birthday = birthday;
	 }

	 public Date getBirthday(){
		 return this.birthday;
	 }

	 public void setPicUrl(String picUrl){
		 this.picUrl = picUrl == null ? null : picUrl.trim();
	 }

	 public String getPicUrl(){
		 return this.picUrl;
	 }

	 public void setIsInitPwd(Integer isInitPwd){
		 this.isInitPwd = isInitPwd;
	 }

	 public Integer getIsInitPwd(){
		 return this.isInitPwd;
	 }

	 public void setStatus(Integer status){
		 this.status = status;
	 }

	 public Integer getStatus(){
		 return this.status;
	 }

	 public void setJoinTime(Date joinTime){
		 this.joinTime = joinTime;
	 }

	 public Date getJoinTime(){
		 return this.joinTime;
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
