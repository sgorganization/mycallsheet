package com.sg.mycallsheet.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	@RequestMapping(value = {"/", "/welcome**"},method = RequestMethod.GET)
	public ModelAndView defaultPage(){
		ModelAndView model = new ModelAndView();
		model.addObject("title","My Call Sheet Login");
		model.addObject("message"," This is default page");
		model.setViewName("hello");
		return model;
	}
	
	@RequestMapping(value="/admin**", method=RequestMethod.GET)
	public ModelAndView adminPage(){
		ModelAndView model = new ModelAndView();
		model.addObject("title","My Call Sheet Login");
		model.addObject("message","This is for ROLE_ADMIN only");
		return model;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView login(@RequestParam(value="error",required=false) String error,
			@RequestParam(value="logout",required=false) String logout,
			HttpServletRequest request){
		
		ModelAndView model = new ModelAndView();
		
		if(error != null){
			model.addObject("error",getErrorMessage(request,"SPRING_SECURITY_LAST_EXCEPTION"));
		}
		
		if(logout != null){
			model.addObject("msg","You have been loggedout successfully.");
		}
		
		model.setViewName("login");
		
		return model;
	}
	
	//Customize the error message
	private String getErrorMessage(HttpServletRequest request,String key){
		Exception exception = (Exception) request.getSession().getAttribute(key);
		String error=null;
		if(exception instanceof BadCredentialsException){
			error = "Invalid Username or Password !";
		}else if(exception instanceof LockedException){
			error = exception.getMessage();
		}else{
			error = "Invalid Username or Password !!";
		}
		return error;
	}
	
	//For 403 Access Denied Page
	@RequestMapping(value="/403", method=RequestMethod.GET)
	public ModelAndView accessDenied(){
		ModelAndView model = new ModelAndView();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(!(auth instanceof AnonymousAuthenticationToken)){
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);
			
			model.addObject("username",userDetail.getUsername());
		}
		model.setViewName("403");
		return model;
	}
	
	
}
