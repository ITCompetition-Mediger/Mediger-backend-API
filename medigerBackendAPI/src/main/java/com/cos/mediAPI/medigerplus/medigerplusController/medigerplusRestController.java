package com.cos.mediAPI.medigerplus.medigerplusController;

import java.time.LocalDate;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.mediAPI.home.mediModel.druglist;
import com.cos.mediAPI.home.searchRepository.searchRepository;
import com.cos.mediAPI.login.SessionUser;
import com.cos.mediAPI.login.User;
import com.cos.mediAPI.login.UserRepository;
import com.cos.mediAPI.medigerplus.medigerplusModel.eatTime;
import com.cos.mediAPI.medigerplus.medigerplusModel.medigerplus;
import com.cos.mediAPI.medigerplus.medigerplusModel.time;
import com.cos.mediAPI.medigerplus.medigerplusRepository.medigerplusRepository;


public class medigerplusRestController {
	@Autowired
	UserRepository uRepository;
	@Autowired
	medigerplusRepository mRepository;
	
	@PostMapping("/home/meidgerplus")
	public String join(HttpSession httpSession, LocalDate SD, LocalDate LD, String How, eatTime how, int many, time T) {
		medigerplus mp = new medigerplus();
		SessionUser user = (SessionUser) httpSession.getAttribute("user");
		Long id = user.getId();
		User use = uRepository.getById(id); 
		mp.setUser(use);
		mp.setStartDate(SD);
		mp.setLastDate(LD);
		mp.setHow(how);
		mp.setMany(many);
		mp.setTimes(T);
		mRepository.save(mp);
		return "설정되었습니다";
	}
		
		
}
