package com.java.spring.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.spring.dao.Studentdao;

@Controller
public class StudentController {
	@Autowired
	Studentdao dao;
//	
//	@RequestMapping("/test")
//	@ResponseBody
//	public JSONObject run()
//	{
//		return dao.studentall();
//	}
//	
	
	
	@RequestMapping("/test")
	@ResponseBody
	public String run()
	{
	
		return dao.studentall().toString();
	}
}
