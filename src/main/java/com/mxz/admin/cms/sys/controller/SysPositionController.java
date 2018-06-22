package com.mxz.admin.cms.sys.controller;

import com.comm.cms.pojo.SysPosition;
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
import com.mxz.admin.cms.sys.service.SysPositionService;

@Controller
@RequestMapping("/sys/position")
public class SysPositionController {

	 @Autowired 
	 private SysPositionService service; 

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
	 @RequestMapping(value="/getById/{id}") 
	 public SysPosition getById(@PathVariable String id) {
		 return service.getById(id);
	 }

	 /**
	  * 
	  * getList的接口
	  * 
	  */
	 @ResponseBody
	 @RequestMapping(value="/getList") 
	 public List<SysPosition> getList(@RequestBody(required=false) Map<String,Object> paramMap) {
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
	 @RequestMapping(value="/getMapList") 
	 public ResponseObject getMapList(@RequestBody(required=false) Map<String,Object> paramMap) {
		 return service.getMapList(paramMap);
	 }

}