package com.comm.cms.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 系统权限表
 * 
 */
public class SysPower implements Serializable {

	 private static final long serialVersionUID = 1L;

	 /**主键id**/
	 private String id;

	 /**父级id**/
	 private String pid;

	 /**名称**/
	 private String lableName;

	 /**权限数字索引**/
	 private Integer powerIndex;

	 /**权限索引名称**/
	 private String powerName;

	 /**webUrl请求地址**/
	 private String webUrl;

	 /**图标路径**/
	 private String imageUrl;

	 /**可访问的action方法地址**/
	 private String actionUrl;

	 /**排序**/
	 private Integer sort;

	 /**导航|权限**/
	 private String style;

	 /**描述**/
	 private String description;

	 /**目录指引**/
	 private String dirName;

	 /**权限级别**/
	 private Integer level;

	 /**是否是最终节点0.不是1.是**/
	 private Integer isFinal;

	 /**创建时间**/
	 private Date createTime;


	 public void setId(String id){
		 this.id = id == null ? null : id.trim();
	 }

	 public String getId(){
		 return this.id;
	 }

	 public void setPid(String pid){
		 this.pid = pid == null ? null : pid.trim();
	 }

	 public String getPid(){
		 return this.pid;
	 }

	 public void setLableName(String lableName){
		 this.lableName = lableName == null ? null : lableName.trim();
	 }

	 public String getLableName(){
		 return this.lableName;
	 }

	 public void setPowerIndex(Integer powerIndex){
		 this.powerIndex = powerIndex;
	 }

	 public Integer getPowerIndex(){
		 return this.powerIndex;
	 }

	 public void setPowerName(String powerName){
		 this.powerName = powerName == null ? null : powerName.trim();
	 }

	 public String getPowerName(){
		 return this.powerName;
	 }

	 public void setWebUrl(String webUrl){
		 this.webUrl = webUrl == null ? null : webUrl.trim();
	 }

	 public String getWebUrl(){
		 return this.webUrl;
	 }

	 public void setImageUrl(String imageUrl){
		 this.imageUrl = imageUrl == null ? null : imageUrl.trim();
	 }

	 public String getImageUrl(){
		 return this.imageUrl;
	 }

	 public void setActionUrl(String actionUrl){
		 this.actionUrl = actionUrl == null ? null : actionUrl.trim();
	 }

	 public String getActionUrl(){
		 return this.actionUrl;
	 }

	 public void setSort(Integer sort){
		 this.sort = sort;
	 }

	 public Integer getSort(){
		 return this.sort;
	 }

	 public void setStyle(String style){
		 this.style = style == null ? null : style.trim();
	 }

	 public String getStyle(){
		 return this.style;
	 }

	 public void setDescription(String description){
		 this.description = description == null ? null : description.trim();
	 }

	 public String getDescription(){
		 return this.description;
	 }

	 public void setDirName(String dirName){
		 this.dirName = dirName == null ? null : dirName.trim();
	 }

	 public String getDirName(){
		 return this.dirName;
	 }

	 public void setLevel(Integer level){
		 this.level = level;
	 }

	 public Integer getLevel(){
		 return this.level;
	 }

	 public void setIsFinal(Integer isFinal){
		 this.isFinal = isFinal;
	 }

	 public Integer getIsFinal(){
		 return this.isFinal;
	 }

	 public void setCreateTime(Date createTime){
		 this.createTime = createTime;
	 }

	 public Date getCreateTime(){
		 return this.createTime;
	 }

}
