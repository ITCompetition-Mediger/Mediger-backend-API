package com.cos.mediAPI.medigerplus.medigerplusController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
import com.cos.mediAPI.medigerplus.medigerplusModel.eatTime;
import com.cos.mediAPI.medigerplus.medigerplusModel.medigerplus;
import com.cos.mediAPI.medigerplus.medigerplusModel.medigerplusDaily;
import com.cos.mediAPI.medigerplus.medigerplusModel.medigerplusMypage;
import com.cos.mediAPI.medigerplus.medigerplusModel.medigerplusMypageDaily;
import com.cos.mediAPI.medigerplus.medigerplusModel.medigerplusMypageList;
import com.cos.mediAPI.medigerplus.medigerplusModel.time;
import com.cos.mediAPI.medigerplus.medigerplusRepository.medigerplusRepository;

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

	@GetMapping("/home/mypage")
	public medigerplusMypage myPage(HttpSession httpSession) {
		SessionUser user = (SessionUser) httpSession.getAttribute("user");
		String Name = user.getName();
		Long id = user.getId();
		List<Scrap> medigerList = scRepository.getAllByUser_id(id);
		List<medigerplus> medigerplus = mRepository.getByUser_Id(id);
		List<medigerplusMypageList> scList = new ArrayList<>();
		medigerplusMypage mdpm = new medigerplusMypage();
		List<medigerplusMypageDaily> dailyList = new ArrayList<>();
		for (int i =0; i<medigerList.size(); i++) {
			medigerplusMypageList plusList = new medigerplusMypageList();
			plusList.setItemName(medigerList.get(i).getDrug().getItemName());
			plusList.setItemImage(medigerList.get(i).getDrug().getItemImage());
			scList.add(plusList);
		}
		for (int i =0; i<medigerplus.size(); i++) {
			medigerplusMypageDaily daily = new medigerplusMypageDaily();
			daily.setItemImage(medigerplus.get(i).getItemSeq().getItemImage());
			daily.setTime(medigerplus.get(i).getTimes());
			daily.setStartDate(medigerplus.get(i).getStartDate());
			daily.setLastDate(medigerplus.get(i).getLastDate());
			dailyList.add(daily);
		}
		mdpm.setUserName(Name);
		mdpm.setDaily(dailyList);
		mdpm.setList(scList);
		return mdpm;

	}
	@PostMapping("/home/medigerplus")
	public String join(HttpSession httpSession,@RequestParam  String ItemName, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate SD, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate LD, @RequestParam eatTime how, @RequestParam int many, @RequestParam time T) {
		medigerplus mp = new medigerplus();
		SessionUser user = (SessionUser) httpSession.getAttribute("user");
		Long id = user.getId();
		User use = uRepository.getById(id); 
		mp.setUser(use);
		druglist dl = sRepository.getByItemName(ItemName);
		try {
		Long medigerItemSeq=dl.getItemSeq();
		}catch(NullPointerException e) {
			e.printStackTrace();
		}
		mp.setItemSeq(dl);
		mp.setStartDate(SD);
		mp.setLastDate(LD);
		mp.setHow(how);
		mp.setMany(many);
		mp.setTimes(T);
		mRepository.save(mp);
		return "설정되었습니다";
	}
	@GetMapping("/home/daily")
	public List<medigerplusDaily> daily(HttpSession httpSession) {
		SessionUser user = (SessionUser) httpSession.getAttribute("user");
		Long id = user.getId();

		List<medigerplus> mt =mRepository.findByUser_Id(id);
		List<medigerplusDaily> lmd = new ArrayList<>();
		for (int i=0; i<mt.size(); i++ ) {
		medigerplusDaily mpd = new medigerplusDaily();
		druglist dl =mt.get(i).getItemSeq();
		mpd.setItemImage(dl.getItemImage());
		mpd.setItemName(dl.getItemName());
		mpd.setHow(mt.get(i).getHow());
		mpd.setMany(mt.get(i).getMany());
		mpd.setT(mt.get(i).getTimes());
		lmd.add(mpd);
		}
		return lmd;
		
	}
//	@GetMapping("/home/scrap")//스크랩 목록 보기
//	public List<List<drugSearchList>> scrapList(HttpSession httpSession){
//		SessionUser user = (SessionUser) httpSession.getAttribute("user");
//		Long id = user.getId();
//		System.out.println(id);
//		List<Scrap> scrapList = scrapRepository.findAllByUser_id(id);
//		List<List<drugSearchList>> boardList = new ArrayList<>();
//		for (int i = 0; i < scrapList.size(); i++) {
//			boardList.add(sRepository.getByItemSeq(scrapList.get(i).getDrug().getItemSeq()));
//			
//		}
//		return boardList;
		
}
