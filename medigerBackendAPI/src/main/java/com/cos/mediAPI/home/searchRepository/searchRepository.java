package com.cos.mediAPI.home.searchRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.mediAPI.home.mediModel.Scrap;
import com.cos.mediAPI.home.mediModel.druglist;

public interface searchRepository extends JpaRepository<druglist,Long> {

	List<druglist> findByItemNameContaining(String itemName);
	
	List<druglist> findByEfcyQesitmContaining(String efcyQesitm);
	
	druglist getByItemSeq(Long itemSeq);
}
