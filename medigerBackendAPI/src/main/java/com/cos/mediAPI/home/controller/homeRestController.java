package com.cos.mediAPI.home.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.mediAPI.home.searchRepository.searchRepository;
import com.cos.mediAPI.login.SessionUser;

import java.util.Optional.*;
import java.util.List;
import java.util.function.Supplier;

import javax.servlet.http.HttpServletRequest;
import java.util.function.*;
import com.cos.mediAPI.home.mediDAO.druglist;
@RestController
public class homeRestController {
	@Autowired
	searchRepository sRepository;
	@GetMapping("/home")
	public String home(HttpSession httpSession) {
		SessionUser user = (SessionUser) httpSession.getAttribute("user");
		String name = user.getName();
		return name+"안녕하세요";
	}
	@GetMapping("/home/search")
	public List<druglist> search(@PathVariable String itemName) {
		List<druglist> drug= sRepository.findByItemName(itemName);
		return drug;
	}
}
