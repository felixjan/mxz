package com.comm.cms.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 会员表
 * 
 */
public class CmsCustomer implements Serializable {

	 private static final long serialVersionUID = 1L;

	 /**主键id**/
	 private String id;

	 /**用户名**/
	 private String userName;

	 /**登录名**/
	 private String loginName;

	 /**密码**/
	 private String password;

	 /**真实姓名**/
	 private String realName;

	 /**昵称**/
	 private String nickName;

	 /**会员头像**/
	 private String logo;

	 /**会员类型**/
	 private Integer type;

	 /**注册来源：wap|pc|ios|android|ipad|pad**/
	 private String source;

	 /**手机号**/
	 private String phone;

	 /**电子邮箱**/
	 private String email;

	 /**QQ号**/
	 private String qq;

	 /**微信号**/
	 private String weixin;

	 /**性别(1.男2.女)**/
	 private Integer sex;

	 /**生日**/
	 private Date birthday;

	 /**国籍**/
	 private Integer nationality;

	 /**证件类型1.身份证2.护照3.军官证4.驾照5.回乡证6.其他**/
	 private Integer cardType;

	 /**证件号码**/
	 private String cardNum;

	 /**国家id**/
	 private Integer countryId;

	 /**国家**/
	 private String countryName;

	 /**省id**/
	 private Integer provinceId;

	 /**省份**/
	 private String provinceName;

	 /**市id**/
	 private Integer cityId;

	 /**城市**/
	 private String cityName;

	 /**区id**/
	 private Integer districtId;

	 /**地区**/
	 private String districtName;

	 /**地址**/
	 private String address;

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

	 public void setLoginName(String loginName){
		 this.loginName = loginName == null ? null : loginName.trim();
	 }

	 public String getLoginName(){
		 return this.loginName;
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

	 public void setNickName(String nickName){
		 this.nickName = nickName == null ? null : nickName.trim();
	 }

	 public String getNickName(){
		 return this.nickName;
	 }

	 public void setLogo(String logo){
		 this.logo = logo == null ? null : logo.trim();
	 }

	 public String getLogo(){
		 return this.logo;
	 }

	 public void setType(Integer type){
		 this.type = type;
	 }

	 public Integer getType(){
		 return this.type;
	 }

	 public void setSource(String source){
		 this.source = source == null ? null : source.trim();
	 }

	 public String getSource(){
		 return this.source;
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

	 public void setWeixin(String weixin){
		 this.weixin = weixin == null ? null : weixin.trim();
	 }

	 public String getWeixin(){
		 return this.weixin;
	 }

	 public void setSex(Integer sex){
		 this.sex = sex;
	 }

	 public Integer getSex(){
		 return this.sex;
	 }

	 public void setBirthday(Date birthday){
		 this.birthday = birthday;
	 }

	 public Date getBirthday(){
		 return this.birthday;
	 }

	 public void setNationality(Integer nationality){
		 this.nationality = nationality;
	 }

	 public Integer getNationality(){
		 return this.nationality;
	 }

	 public void setCardType(Integer cardType){
		 this.cardType = cardType;
	 }

	 public Integer getCardType(){
		 return this.cardType;
	 }

	 public void setCardNum(String cardNum){
		 this.cardNum = cardNum == null ? null : cardNum.trim();
	 }

	 public String getCardNum(){
		 return this.cardNum;
	 }

	 public void setCountryId(Integer countryId){
		 this.countryId = countryId;
	 }

	 public Integer getCountryId(){
		 return this.countryId;
	 }

	 public void setCountryName(String countryName){
		 this.countryName = countryName == null ? null : countryName.trim();
	 }

	 public String getCountryName(){
		 return this.countryName;
	 }

	 public void setProvinceId(Integer provinceId){
		 this.provinceId = provinceId;
	 }

	 public Integer getProvinceId(){
		 return this.provinceId;
	 }

	 public void setProvinceName(String provinceName){
		 this.provinceName = provinceName == null ? null : provinceName.trim();
	 }

	 public String getProvinceName(){
		 return this.provinceName;
	 }

	 public void setCityId(Integer cityId){
		 this.cityId = cityId;
	 }

	 public Integer getCityId(){
		 return this.cityId;
	 }

	 public void setCityName(String cityName){
		 this.cityName = cityName == null ? null : cityName.trim();
	 }

	 public String getCityName(){
		 return this.cityName;
	 }

	 public void setDistrictId(Integer districtId){
		 this.districtId = districtId;
	 }

	 public Integer getDistrictId(){
		 return this.districtId;
	 }

	 public void setDistrictName(String districtName){
		 this.districtName = districtName == null ? null : districtName.trim();
	 }

	 public String getDistrictName(){
		 return this.districtName;
	 }

	 public void setAddress(String address){
		 this.address = address == null ? null : address.trim();
	 }

	 public String getAddress(){
		 return this.address;
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
