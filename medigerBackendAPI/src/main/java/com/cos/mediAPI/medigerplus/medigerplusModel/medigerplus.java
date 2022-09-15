package com.cos.mediAPI.medigerplus.medigerplusModel;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cos.mediAPI.home.mediModel.druglist;
import com.cos.mediAPI.login.User;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor //bean생성자
@Entity
@Table(name="medigerplus")
public class medigerplus implements Serializable {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long medigerplusId;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(nullable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate startDate;
	
	@Column(nullable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate lastDate;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "drug_itemSeq")
	private druglist itemSeq;

	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private eatTime how;
	
	@Column(nullable=false)
	private int many;
	
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private time times;
}
