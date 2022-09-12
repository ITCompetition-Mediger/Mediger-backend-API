package com.cos.mediAPI.home.mediModel;

import java.io.Serializable;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cos.mediAPI.home.homeDAO.ScrapId;
import com.cos.mediAPI.login.User;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "scrap")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(ScrapId.class)
public class Scrap implements Serializable {
	@Id
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "id")
	private User user;
	
	@Id
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "itemSeq")
	private druglist drug;
	

	
}
