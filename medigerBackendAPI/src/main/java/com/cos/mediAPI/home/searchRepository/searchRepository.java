package com.cos.mediAPI.home.searchRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.mediAPI.home.mediDAO.druglist;

public interface searchRepository extends JpaRepository<druglist,Long> {

	List<druglist> findByItemNameContaining(String itemName);
	
	List<druglist> findByEfcyQesitmContaining(String efcyQesitm);
}
