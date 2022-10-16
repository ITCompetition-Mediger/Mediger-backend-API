package com.cos.mediAPI.medigerplus.medigerplusModel;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class medigerplusMypageDaily {
	Long id;
	String itemName;
	String itemImage;
	time time;
	LocalDate startDate;
	LocalDate lastDate;
}
