package com.cos.mediAPI.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class homeController {
	
	@GetMapping("/login-success")
	public String loginSuccess() {
		return "redirect:/home";
	}
//	@GetMapping("/admin/logout.do")
//	public String loout(HttpServletRequest request, HttpServletResponse response) throws Exception { 
//	
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();       
//		if (auth != null) {            new SecurityContextLogoutHandler().logout(request, response, auth);        } 
//		return "redirect:/admin/login.do";    }
//	출처: https://baejangho.com/entry/Spring-Security-Logout [호짱의 개발 블로그:티스토리]

}
