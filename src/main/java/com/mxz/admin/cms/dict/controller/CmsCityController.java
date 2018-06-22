package com.mxz.admin.cms.dict.controller;

import com.comm.cms.pojo.CmsCity;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.comm.util.MapUtils;
import com.comm.util.Page;
import com.comm.util.ResponseObject;
import com.mxz.admin.cms.dict.service.CmsCityService;

@Controller
@RequestMapping("/cms/city")
public class CmsCityController {

	 @Autowired 
	 private CmsCityService service; 

	 /**
	  * 
	  * 新增数据的接口
	  * 
	  */
	 @ResponseBody
	 @RequestMapping(value="/create",method=RequestMethod.POST) 
	 public ResponseObject create(@RequestBody(required=false) Map<String,Object> paramMap) {
		 return service.create(paramMap);
	 }

	 /**
	  * 
	  * 修改数据的接口
	  * 
	  */
	 @ResponseBody
	 @RequestMapping(value="/update",method=RequestMethod.POST) 
	 public ResponseObject update(@RequestBody(required=false) Map<String,Object> paramMap) {
		 return service.update(paramMap);
	 }

	 /**
	  * 
	  * 根据id获取数据的接口
	  * 
	  */
	 @ResponseBody
	 @RequestMapping(value="/getById/{id}",method=RequestMethod.GET) 
	 public CmsCity getById(@PathVariable int id) {
		 return service.getById(id);
	 }

	 /**
	  * 
	  * getList的接口
	  * 
	  */
	 @ResponseBody
	 @RequestMapping(value="/getList",method=RequestMethod.GET) 
	 public List<CmsCity> getList(@RequestBody(required=false) Map<String,Object> paramMap) {
		 return service.getList(paramMap);
	 }

	 /**
	  * 
	  * getPageList的接口
	  * 
	  */
	 @ResponseBody
	 @RequestMapping(value="/getPageList",method=RequestMethod.POST) 
	 public Page getPageList(String queryJson,int page,int limit) {
		 return service.getPageList(MapUtils.JsonToMap(queryJson, page, limit));
	 }

	 /**
	  * 
	  * getMapList的接口
	  * 
	  */
	 @ResponseBody
	 @RequestMapping(value="/getMapList",method=RequestMethod.GET) 
	 public ResponseObject getMapList(@RequestBody(required=false) Map<String,Object> paramMap) {
		 return service.getMapList(paramMap);
	 }

}