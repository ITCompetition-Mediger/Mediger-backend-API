package com.cos.mediAPI.foreign.Home_EN.Controller;

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

import com.cos.mediAPI.home.homeDAO.ScrapId;
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
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import javax.servlet.http.HttpServletRequest;
import java.util.function.*;
@RestController
public class EN_HomeRestController {
	@Autowired
	UserRepository uRepository;
	@Autowired
	searchRepository sRepository;
	@Autowired
	ScrapRepository scrapRepository;

	@GetMapping("/home_EN")
	public String home(HttpSession httpSession) {
		SessionUser user = (SessionUser) httpSession.getAttribute("user");
		String Name = user.getName();
		return Name+"Hello";
	}
	@GetMapping("/home_EN/searchByItemName")
	public List<drugSearchList> search(@RequestParam String itemName) {
		List<drugSearchList> drug= sRepository.findByItemNameContaining(itemName);
		return drug;
	}
	@GetMapping("/home_EN/searchByItemSeq/Detail")// itemSeq로 상세보기
	public druglist detail(@RequestParam Long itemSeq) {
		druglist drug= sRepository.findByItemSeq(itemSeq);
		return drug;
	}
	@GetMapping("/home_EN/searchByEfcy")
	public List<drugSearchList> search2(@RequestParam String efcyQesitm){
		List<drugSearchList> drug= sRepository.findByEfcyQesitmContaining(efcyQesitm);
		return drug;
	}
	
	@GetMapping("/home_EN/scrap")//스크랩 목록 보기
	public List<List<drugSearchList>> scrapList(HttpSession httpSession){
		SessionUser user = (SessionUser) httpSession.getAttribute("user");
		Long id = user.getId();
		System.out.println(id);
		List<Scrap> scrapList = scrapRepository.findAllByUser_id(id);
		List<List<drugSearchList>> boardList = new ArrayList<>();
		for (int i = 0; i < scrapList.size(); i++) {
			boardList.add(sRepository.getByItemSeq(scrapList.get(i).getDrug().getItemSeq()));
			
		}
		return boardList;
	}
	@PostMapping("/home_EN/scrap")//스크랩추가. 현재 Postmapping으로 구현했으나, requestbody 어노테이션을 Pathvariable로 전환하여 구현도 가능.
	public Object userScrapAdd(HttpSession httpSession, @RequestParam Long itemSeq) {
		Scrap newScrap = new Scrap();
		SessionUser user = (SessionUser) httpSession.getAttribute("user");
		Long id = user.getId();
		System.out.println(id);
		System.out.println(itemSeq);
		User use = uRepository.getById(id);
		druglist dlist = sRepository.findByItemSeq(itemSeq);
		newScrap.setDrug(dlist);
		newScrap.setUser(use);
		scrapRepository.save(newScrap);

		return "Success!"; //성공
	}
	
	@DeleteMapping("/home_EN/scrap")
	public Object userScrapRemove(HttpSession httpSession, @RequestParam Long itemSeq) {
		Scrap deleteScrap = new Scrap();
		SessionUser user = (SessionUser) httpSession.getAttribute("user");
		Long id = user.getId();
		User use = uRepository.getById(id);
		druglist dlist = sRepository.findByItemSeq(itemSeq);
		deleteScrap.setDrug(dlist);
		deleteScrap.setUser(use);
		scrapRepository.delete(deleteScrap);
		return "Deleted"; //삭제
	}
	
}
