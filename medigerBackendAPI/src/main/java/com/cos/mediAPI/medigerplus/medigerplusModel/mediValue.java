package com.cos.mediAPI.medigerplus.medigerplusModel;

import java.time.LocalDate;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class mediValue {
	private String ItemName;
	private LocalDate SD;
	private LocalDate LD;
	private eatTime how;
	private int many;
	private time T;
	//@RequestBody  String ItemName, @RequestBody @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate SD, @RequestBody @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate LD, @RequestBody eatTime how, @RequestBody int many, @RequestBody time T
}
