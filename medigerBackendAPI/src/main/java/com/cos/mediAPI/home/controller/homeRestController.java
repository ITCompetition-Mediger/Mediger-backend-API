package com.cos.mediAPI.home.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cos.mediAPI.home.mediModel.Scrap;
import com.cos.mediAPI.home.mediModel.drugSearchList;
import com.cos.mediAPI.home.mediModel.druglist;
import com.cos.mediAPI.home.searchRepository.ScrapRepository;
import com.cos.mediAPI.home.searchRepository.searchRepository;
import com.cos.mediAPI.login.SessionUser;
import com.cos.mediAPI.login.User;
import com.cos.mediAPI.login.UserRepository;

import java.util.Optional.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import javax.servlet.http.HttpServletRequest;
import java.util.function.*;
@RestController
public class homeRestController {
	@Autowired
	UserRepository uRepository;
	@Autowired
	searchRepository sRepository;
	@Autowired
	ScrapRepository scrapRepository;
	
	
	@GetMapping("/home")
	public String home(HttpSession httpSession) {
		SessionUser user = (SessionUser) httpSession.getAttribute("user");
		Long id = user.getId();
		return id+"안녕하세요";
	}
	@GetMapping("/home/searchByItemName")
	public List<drugSearchList> search(@RequestParam String itemName) {
		List<drugSearchList> drug= sRepository.findByItemNameContaining(itemName);
		return drug;
	}
	@GetMapping("/home/searchByItemName/Detail")// itemSeq로 상세보기
	public druglist detail(@RequestParam Long itemSeq) {
		druglist drug= sRepository.getByItemSeq(itemSeq);
		return drug;
	}
	@GetMapping("/home/searchByEfcy")
	public List<drugSearchList> search2(@RequestParam String efcyQesitm){
		List<drugSearchList> drug= sRepository.findByEfcyQesitmContaining(efcyQesitm);
		return drug;
	}
	
	@GetMapping("/home/scrap")//스크랩 목록 보기
	public List<druglist> scrapList(HttpSession httpSession){
		SessionUser user = (SessionUser) httpSession.getAttribute("user");
		Long id = user.getId();
		System.out.println(id);
		List<Scrap> scrapList = scrapRepository.findAllByUser_id(id);
		List<druglist> boardList = new ArrayList<>();
		for (int i = 0; i < scrapList.size(); i++) {
			boardList.add(scrapList.get(i).getDrug());
		}
		return boardList;
	}
	@PostMapping("/home/scrap")//스크랩추가. 현재 Postmapping으로 구현했으나, requestbody 어노테이션을 Pathvariable로 전환하여 구현도 가능.
	public Object userScrapAdd(HttpSession httpSession, @RequestParam Long itemSeq) {
		Scrap newScrap = new Scrap();
		SessionUser user = (SessionUser) httpSession.getAttribute("user");
		Long id = user.getId();
		System.out.println(id);
		System.out.println(itemSeq);
		User use = uRepository.getById(id);
		druglist dlist = sRepository.getByItemSeq(itemSeq);
		newScrap.setDrug(dlist);
		newScrap.setUser(use);
		scrapRepository.save(newScrap);

		return "스크랩 되었습니다.";
	}
	
	@DeleteMapping("/user/scrap")
	public Object userScrapRemove(HttpSession httpSession, @RequestParam Long itemSeq) {
		Scrap deleteScrap = new Scrap();
		SessionUser user = (SessionUser) httpSession.getAttribute("user");
		Long id = user.getId();
		User use = uRepository.getById(id);
		druglist dlist = sRepository.getByItemSeq(itemSeq);
		deleteScrap.setDrug(dlist);
		deleteScrap.setUser(use);
		scrapRepository.delete(deleteScrap);
		return "스크랩이 취소되었습니다";
	}
	
}
