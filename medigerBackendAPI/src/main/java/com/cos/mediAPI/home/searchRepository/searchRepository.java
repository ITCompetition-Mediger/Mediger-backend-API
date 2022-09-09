package com.cos.mediAPI.home.searchRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.mediAPI.home.mediDAO.druglist;
import com.cos.mediAPI.login.User;

public interface searchRepository extends JpaRepository<druglist,Long> {

	List<druglist> findByItemName(String itemName);
	
}
