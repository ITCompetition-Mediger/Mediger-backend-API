package com.cos.mediAPI.medigerplus.medigerplusRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.mediAPI.medigerplus.medigerplusModel.medigerplus;
import com.cos.mediAPI.medigerplus.medigerplusModel.medigerplusDaily;

public interface medigerplusRepository extends JpaRepository<medigerplus, Long> {
	List<medigerplus> getByUser_Id(Long id);
	List<medigerplus> findByUser_Id(Long id);
	
}
