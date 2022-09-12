package com.cos.mediAPI.home.mediModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 //bean생성자
public interface drugSearchList {
	String getItemImage();
	String getItemName();
	String setItemName(String IName);
	Long getItemSeq();
	Long setItemSeq(Long ISeq);
	String getEntpName();
	String setEntpName(String EName);
}
