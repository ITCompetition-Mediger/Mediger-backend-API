package com.cos.mediAPI.home.homeDAO;

import java.io.Serializable;

public class ScrapId implements Serializable{
	private Long user;
	private Long drug;
	
	public ScrapId() {}
	public ScrapId(Long user, Long drug) {
		super();
		this.user= user;
		this.drug = drug;
	}
	
}
