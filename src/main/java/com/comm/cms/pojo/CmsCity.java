package com.comm.cms.pojo;

import java.io.Serializable;
/**
 * 
 * 城市字典表
 * 
 */
public class CmsCity implements Serializable {

	 private static final long serialVersionUID = 1L;

	 /**主键id**/
	 private Integer id;

	 /**名称**/
	 private String name;

	 /**父id**/
	 private Integer pid;

	 /**级别**/
	 private Integer level;

	 /**首字母**/
	 private String szm;

	 /**全拼音**/
	 private String pingyin;


	 public void setId(Integer id){
		 this.id = id;
	 }

	 public Integer getId(){
		 return this.id;
	 }

	 public void setName(String name){
		 this.name = name == null ? null : name.trim();
	 }

	 public String getName(){
		 return this.name;
	 }

	 public void setPid(Integer pid){
		 this.pid = pid;
	 }

	 public Integer getPid(){
		 return this.pid;
	 }

	 public void setLevel(Integer level){
		 this.level = level;
	 }

	 public Integer getLevel(){
		 return this.level;
	 }

	 public void setSzm(String szm){
		 this.szm = szm == null ? null : szm.trim();
	 }

	 public String getSzm(){
		 return this.szm;
	 }

	 public void setPingyin(String pingyin){
		 this.pingyin = pingyin == null ? null : pingyin.trim();
	 }

	 public String getPingyin(){
		 return this.pingyin;
	 }

}
