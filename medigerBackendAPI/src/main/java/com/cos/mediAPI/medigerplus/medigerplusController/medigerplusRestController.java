package com.cos.mediAPI.medigerplus.medigerplusController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cos.mediAPI.home.mediModel.Scrap;
import com.cos.mediAPI.home.mediModel.ScrapList;
import com.cos.mediAPI.home.mediModel.drugSearchList;
import com.cos.mediAPI.home.mediModel.druglist;
import com.cos.mediAPI.home.searchRepository.ScrapRepository;
import com.cos.mediAPI.home.searchRepository.searchRepository;
import com.cos.mediAPI.login.SessionUser;
import com.cos.mediAPI.login.User;
import com.cos.mediAPI.login.UserRepository;
import com.cos.mediAPI.medigerplus.medigerplusModel.JSONMedigerBody;
import com.cos.mediAPI.medigerplus.medigerplusModel.eatTime;
import com.cos.mediAPI.medigerplus.medigerplusModel.medigerplus;
import com.cos.mediAPI.medigerplus.medigerplusModel.medigerplusDaily;
import com.cos.mediAPI.medigerplus.medigerplusModel.medigerplusMypage;
import com.cos.mediAPI.medigerplus.medigerplusModel.medigerplusMypageDaily;
import com.cos.mediAPI.medigerplus.medigerplusModel.medigerplusMypageList;
import com.cos.mediAPI.medigerplus.medigerplusModel.time;
import com.cos.mediAPI.medigerplus.medigerplusRepository.medigerplusRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class medigerplusRestController {
	@Autowired
	UserRepository uRepository;
	@Autowired
	medigerplusRepository mRepository;
	@Autowired
	searchRepository sRepository;
	@Autowired
	ScrapRepository scRepository;
	static Long id;

	@GetMapping("/home/mypage")
	public medigerplusMypage myPage(HttpSession httpSession) {
		SessionUser user = (SessionUser) httpSession.getAttribute("user");
		String Name = user.getName();

		id = user.getId();
		List<Scrap> medigerList = scRepository.getAllByUser_id(id);
		List<druglist> scrapList = new ArrayList<>();
		medigerplusMypage mdpm = new medigerplusMypage();
		for (int i =0; i<medigerList.size(); i++) {
			druglist drugList =medigerList.get(i).getDrug();
			drugList.setAtpnQesitm(drugList.getAtpnQesitm().replaceAll("<p>", "").replaceAll("</p>", ""));
			drugList.setAtpnWarnQesitm(drugList.getAtpnWarnQesitm().replaceAll("<p>", "").replaceAll("</p>", ""));
			drugList.setEfcyQesitm(drugList.getEfcyQesitm().replaceAll("<p>", "").replaceAll("</p>", ""));
			drugList.setIntrcQesitm(drugList.getIntrcQesitm().replaceAll("<p>", "").replaceAll("</p>", ""));
			drugList.setSeQesitm(drugList.getSeQesitm().replaceAll("<p>", "").replaceAll("</p>", ""));
			drugList.setUseMethodQesitm(drugList.getUseMethodQesitm().replaceAll("<p>", "").replaceAll("</p>", ""));
			drugList.setDepositMethodQesitm(drugList.getDepositMethodQesitm().replaceAll("<p>", "").replaceAll("</p>", ""));
			scrapList.add(drugList);
		}
//		for (int i =0; i<medigerplus.size(); i++) {
//			medigerplusMypageDaily daily = new medigerplusMypageDaily();
//			daily.setItemName(medigerplus.get(i).getItemSeq().getItemName());
//			daily.setItemImage(medigerplus.get(i).getItemSeq().getItemImage());
//			daily.setTime(medigerplus.get(i).getTimes());
//			daily.setStartDate(medigerplus.get(i).getStartDate());
//			daily.setLastDate(medigerplus.get(i).getLastDate());
//			dailyList.add(daily);
//		}
		List<medigerplus> mt =mRepository.findByUser_Id(id);
		List<medigerplusDaily> lmd = new ArrayList<>();
		for (int i=0; i<mt.size(); i++ ) {
		medigerplusDaily mpd = new medigerplusDaily();
		druglist dl =mt.get(i).getItemSeq();
		dl.setAtpnQesitm(dl.getAtpnQesitm().replaceAll("<p>", "").replaceAll("</p>", ""));
		dl.setAtpnWarnQesitm(dl.getAtpnWarnQesitm().replaceAll("<p>", "").replaceAll("</p>", ""));
		dl.setEfcyQesitm(dl.getEfcyQesitm().replaceAll("<p>", "").replaceAll("</p>", ""));
		dl.setIntrcQesitm(dl.getIntrcQesitm().replaceAll("<p>", "").replaceAll("</p>", ""));
		dl.setSeQesitm(dl.getSeQesitm().replaceAll("<p>", "").replaceAll("</p>", ""));
		dl.setUseMethodQesitm(dl.getUseMethodQesitm().replaceAll("<p>", "").replaceAll("</p>", "") .replaceAll("<sub>","").replaceAll("</sub>","").replaceAll("<sup>","").replaceAll("</sup>",""));
		mpd.setItemImage(dl.getItemImage());
		mpd.setItemName(dl.getItemName());
		mpd.setHow(mt.get(i).getHow());
		mpd.setMany(mt.get(i).getMany());
		mpd.setWhen(mt.get(i).getTimes());
		mpd.setLastDate(mt.get(i).getLastDate());
		mpd.setStartDate(mt.get(i).getStartDate());
		mpd.setMedigerDruglist(dl);
		lmd.add(mpd);
		}
		mdpm.setUserName(Name);
		mdpm.setId(id);
		mdpm.setDaily(lmd);
		mdpm.setScrapList(scrapList);
		return mdpm;

	}
//	@GetMapping("/home/mypage/monthly")
//	public List<medigerplusMypageDaily>monthly(HttpSession httpSession) {
//		SessionUser user = (SessionUser) httpSession.getAttribute("user");
//		String Name = user.getName();
//		Long id = user.getId();
//		List<medigerplus> medigerplus = mRepository.getByUser_Id(id);
//		List<medigerplusMypageDaily> dailyList = new ArrayList<>();
//		for (int i =0; i<medigerplus.size(); i++) {
//			medigerplusMypageDaily daily = new medigerplusMypageDaily();
//			daily.setItemName(medigerplus.get(i).getItemSeq().getItemName());
//			System.out.println(daily.getItemName());
//			daily.setItemImage(medigerplus.get(i).getItemSeq().getItemImage());
//			daily.setTime(medigerplus.get(i).getTimes());
//			daily.setStartDate(medigerplus.get(i).getStartDate());
//			daily.setLastDate(medigerplus.get(i).getLastDate());
//			dailyList.add(daily);
//		}
//		return dailyList;
//		
//	}
	@PostMapping("/home/mypage/medigerplus")
	public void medigerplus( @RequestBody Map<String, String> MedigerBody) {
		String ItemName =(MedigerBody.get("itemName")).toString();
		String JsonHow = (MedigerBody.get("how")).toString();
		int many = Integer.parseInt((MedigerBody.get("many")).toString());
		String JsonWhen = (MedigerBody.get("when")).toString();
		Long id = Long.parseLong(MedigerBody.get("id"));
		time Ti;
		eatTime how;
		String SD = (MedigerBody.get("start").toString()).substring(0,10);
		LocalDate StartDate = LocalDate.parse(SD, DateTimeFormatter.ISO_DATE);
		String LD = (MedigerBody.get("last").toString()).substring(0,10);
		LocalDate LastDate = LocalDate.parse(LD, DateTimeFormatter.ISO_DATE);
		

		System.out.println(LD);
		if ( JsonHow.equals("식전 30분")) {
			how = eatTime.beforeMeal;
		}else if(JsonHow.equals("식사 직후")){
			how = eatTime.Meal;
		}else {
			how = eatTime.afterMeal;
		}
//		localDate //last JSON 변수 선언
		//start JSON 변수 선언
		System.out.println(JsonWhen);
		if ( JsonWhen.equals("아침")) {
			Ti = time.Morn;
		}else if(JsonWhen.equals("점심")){
			Ti = time.After;
		}else {
			Ti = time.Even;
		}
		medigerplus mp = new medigerplus();
		User use = uRepository.getById(id);
		mp.setUser(use);
		druglist dl = sRepository.getByItemName(ItemName);
		try {
		Long medigerItemSeq=dl.getItemSeq();
//		
		}catch(NullPointerException e) {
			e.printStackTrace();
		}
		mp.setItemSeq(dl);
		mp.setStartDate(StartDate);
		mp.setLastDate(LastDate);
		mp.setHow(how);
		mp.setMany(many);
		mp.setTimes(Ti);
		mRepository.save(mp);
	}
//	@GetMapping("/home/mypage/monthly/daily")
//	public List<medigerplusDaily> daily(HttpSession httpSession) {
//		SessionUser user = (SessionUser) httpSession.getAttribute("user");
//		Long id = user.getId();
//
//		List<medigerplus> mt =mRepository.findByUser_Id(id);
//		List<medigerplusDaily> lmd = new ArrayList<>();
//		for (int i=0; i<mt.size(); i++ ) {
//		medigerplusDaily mpd = new medigerplusDaily();
//		druglist dl =mt.get(i).getItemSeq();
//		mpd.setItemImage(dl.getItemImage());
//		mpd.setItemName(dl.getItemName());
//		mpd.setHow(mt.get(i).getHow());
//		mpd.setMany(mt.get(i).getMany());
//		mpd.setWhen(mt.get(i).getTimes());
//		mpd.setLastDate(mt.get(i).getLastDate());
//		mpd.setStartDate(mt.get(i).getStartDate());
//		lmd.add(mpd);
//		}
//		return lmd;
//		
//	}

		
}
