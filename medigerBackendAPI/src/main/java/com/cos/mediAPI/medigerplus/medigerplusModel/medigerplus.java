package com.cos.mediAPI.medigerplus.medigerplusModel;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long medigerplusId;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "user_id")//사용자 세션 id
	private User user;
	
	@Column(nullable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")//시작일
	private LocalDate startDate;
	
	@Column(nullable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")//종료일
	private LocalDate lastDate;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "itemSeq")//알약 일련번호
	private druglist itemSeq;

	@Column(nullable=false)
	@Enumerated(EnumType.STRING) //식전, 식후, 식사
	private eatTime how;
	
	@Column(nullable=false) //복용 알약갯수
	private int many;
	
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)// 복용시간 아침점심저녁
	private time times;
}
