package com.comm.util;

import java.io.Serializable;
import java.util.List;

public class Page implements Serializable{
	private static final long serialVersionUID = 1L;
	private int code; //查询状态 0:返回成功 其他自定义
	private String msg; //查询消息 配合code用 空的话默认"返回的数据状态异常"
    private int count;	//总条数
	private List<?> data; //分页集合
	
	public Page(){
		this.code = 0;
		this.msg = "";
	}
	
	public Page(int code,String msg){
		this.code = code;
        this.msg = msg;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<?> getData() {
		return data;
	}
	public void setData(List<?> data) {
		this.data = data;
	}
	
	
}
