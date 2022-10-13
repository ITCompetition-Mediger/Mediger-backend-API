package com.cos.mediAPI.home.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
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
import com.cos.mediAPI.medigerplus.medigerplusModel.medigerplus;
import com.cos.mediAPI.medigerplus.medigerplusModel.medigerplusMypage;
import com.cos.mediAPI.medigerplus.medigerplusModel.medigerplusMypageDaily;
import com.cos.mediAPI.medigerplus.medigerplusModel.medigerplusMypageList;
import com.cos.mediAPI.medigerplus.medigerplusRepository.medigerplusRepository;

import java.util.Optional.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import javax.servlet.http.HttpServletRequest;
import java.util.function.*;
@CrossOrigin(value = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
public class homeRestController {
	@Autowired
	UserRepository uRepository;
	@Autowired
	searchRepository sRepository;
	@Autowired
	ScrapRepository scrapRepository;
	@Autowired
	medigerplusRepository mRepository;
	@GetMapping("/logout-success")
	public String logout() {
		return "로그아웃되었습니다.";
	}
	@GetMapping("/home")
	public ArrayList home(HttpSession httpSession) {
		SessionUser user = (SessionUser) httpSession.getAttribute("user");
		ArrayList userlist = new ArrayList<>();
		userlist.add(user);
		String Name = user.getName();
		Long id = user.getId();
		List<medigerplus> medigerplus = mRepository.getByUser_Id(id);
		List<medigerplusMypageDaily> dailyList = new ArrayList<>();
		for (int i =0; i<medigerplus.size(); i++) {
			medigerplusMypageDaily daily = new medigerplusMypageDaily();
			daily.setItemName(medigerplus.get(i).getItemSeq().getItemName());
			daily.setItemImage(medigerplus.get(i).getItemSeq().getItemImage());
			daily.setTime(medigerplus.get(i).getTimes());
			daily.setStartDate(medigerplus.get(i).getStartDate());
			daily.setLastDate(medigerplus.get(i).getLastDate());
			dailyList.add(daily);
		}
		userlist.add(dailyList);
		return userlist;
	} 
	@GetMapping("/home/search")
	public List<drugSearchList> search(@RequestParam String type, @RequestParam String keyword) throws Exception {
		List<drugSearchList> drug= new ArrayList<>();
		switch(type)	{
			case "의약품명": 
				drug =sRepository.findByItemNameContaining(keyword);
				break;
			case "증상" :
				drug= sRepository.findByEfcyQesitmContaining(keyword);
				break;
			}
		return drug;
	}
	@GetMapping("/home/searchByItemSeq/Detail")// itemSeq로 상세보기
	public druglist detail(@RequestParam Long itemSeq) {
		druglist drug= sRepository.findByItemSeq(itemSeq);
		drug.setAtpnQesitm(drug.getAtpnQesitm().replaceAll("<p>", "").replaceAll("</p>", ""));
		drug.setAtpnWarnQesitm(drug.getAtpnWarnQesitm().replaceAll("<p>", "").replaceAll("</p>", ""));
		drug.setEfcyQesitm(drug.getEfcyQesitm().replaceAll("<p>", "").replaceAll("</p>", ""));
		drug.setIntrcQesitm(drug.getIntrcQesitm().replaceAll("<p>", "").replaceAll("</p>", ""));
		drug.setSeQesitm(drug.getSeQesitm().replaceAll("<p>", "").replaceAll("</p>", ""));
		drug.setUseMethodQesitm(drug.getUseMethodQesitm().replaceAll("<p>", "").replaceAll("</p>", ""));
		drug.setDepositMethodQesitm(drug.getDepositMethodQesitm().replaceAll("<p>", "").replaceAll("</p>", ""));
		return drug;
	}
	
	@GetMapping("/home/scrap")//스크랩 목록 보기
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
	@PostMapping("/home/scrap")//스크랩추가. 현재 Postmapping으로 구현했으나, requestbody 어노테이션을 Pathvariable로 전환하여 구현도 가능.
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

		return "스크랩 되었습니다.";
	}
	
	@DeleteMapping("/home/scrap")
	public Object userScrapRemove(HttpSession httpSession, @RequestParam Long itemSeq) {
		Scrap deleteScrap = new Scrap();
		SessionUser user = (SessionUser) httpSession.getAttribute("user");
		Long id = user.getId();
		User use = uRepository.getById(id);
		druglist dlist = sRepository.findByItemSeq(itemSeq);
		deleteScrap.setDrug(dlist);
		deleteScrap.setUser(use);
		scrapRepository.delete(deleteScrap);
		return "스크랩이 취소되었습니다";
	}
	
}
