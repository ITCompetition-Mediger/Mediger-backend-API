package com.cos.mediAPI.medigerplus.medigerplusModel;

import java.time.LocalDate;

import com.cos.mediAPI.login.User;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class medigerplusDaily {
	private String itemImage ;
    private String itemName;
    private eatTime how;
    private int many;
    private time when;
    private LocalDate startDate;
    private LocalDate LastDate;

}
