package com.cos.mediAPI.medigerplus.medigerplusModel;

import java.util.List;

import com.cos.mediAPI.home.mediModel.Scrap;
import com.cos.mediAPI.home.mediModel.druglist;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class medigerplusMypage {
	private String userName;
	private List<medigerplusDaily> Daily;
	private List<druglist> List;
}
