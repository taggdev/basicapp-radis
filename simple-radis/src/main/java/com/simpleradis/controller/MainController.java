package com.simpleradis.controller;

import com.google.gson.Gson;
import com.simpleradis.model.Member;
import com.simpleradis.service.SimpleradisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	@Autowired
	protected SimpleradisService memberService;

	@RequestMapping("/")
	 public String home(Model model) {
	  return "/home";
	 }
	

	@RequestMapping(value="/save", method = RequestMethod.GET)
	@ResponseBody
	public String save(@RequestParam("id") String id,@RequestParam("name") String name,@RequestParam("last") String last) {
		memberService.memberSave(new Member(Long.parseLong(id),name,last));
		return "Done";
	}
 
	@RequestMapping(value = "/findall", method = RequestMethod.GET, produces = { "application/json; charset=UTF-8;" })
	@ResponseBody
	public String findAll() {
		return new Gson().toJson( memberService.memberFindAll());
	}
	
	@RequestMapping(value = "/find", method = RequestMethod.GET, produces = { "application/json; charset=UTF-8;" })
	@ResponseBody
	public String find(@RequestParam("id") String id){
		return new Gson().toJson( memberService.memberFindById(id));
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@ResponseBody
	public String deleteById(@RequestParam("id") String id) {
		memberService.memberDeleteById(id);
		return "Done";
	}



}
