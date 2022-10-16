package com.cos.mediAPI.medigerplus.medigerplusModel;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

public class JSONMedigerBody {
	String itemName;
	String start;
	String last;
	String how;
	String many;
	String when;
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String ItemName) {
		itemName = ItemName;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String SD) {
		start = SD;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String LD) {
		last = LD;
	}
	public	String getHow() {
		return how;
	}
	public void setHow(String how) {
		this.how = how;
	}
	public String getMany() {
		return many;
	}
	public void setMany(String many) {
		this.many = many;
	}
	public String getWhen() {
		return when;
	}
	public void setT(String t) {
		when = t;
	}

}
