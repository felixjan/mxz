package com.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mxz.admin.cms.sys.service.SysRoleService;

public class DemoTest extends BaseTest {
	@Autowired
	private SysRoleService service; 
	
	private Map<String, Object> paramMap;
	
//	@Value("${sys.admin.url}") //只能在serviceImpl中应用
//  private String sys_admin_url;
	
	@Test
	public void getPageList(){
		paramMap=new HashMap<String, Object>();
		System.out.println("=============="+service.getPageList(paramMap));
	}
	
	@Test
	public void create(){
		paramMap=new HashMap<String, Object>();
		System.out.println("=============="+service.create(paramMap));
	} 
	
	@Test
	public void update(){
		paramMap=new HashMap<String, Object>();
		System.out.println("=============="+service.update(paramMap));
	} 

}
