package com.cos.mediAPI.home.searchRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.mediAPI.home.homeDAO.ScrapId;
import com.cos.mediAPI.home.mediModel.Scrap;
import com.cos.mediAPI.home.mediModel.drugSearchList;

public interface ScrapRepository extends JpaRepository<Scrap, ScrapId>{
	List<Scrap> findAllByUser_id(Long id);
}
