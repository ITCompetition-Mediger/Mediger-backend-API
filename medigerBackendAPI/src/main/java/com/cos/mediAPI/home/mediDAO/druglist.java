package com.cos.mediAPI.home.mediDAO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor //bean생성자
@Entity
public class druglist {
	@Column
	private String atpnQesitm;
	@Column
	private String atpnWarnQesitm;
	@Column
	private String depositMethodQesitm;
	@Column
	private String efcyQesitm;
	@Column
	private String entpName;
	@Column
	private String intrcQesitm;
	@Column
	private String itemImage;
	@Column
	private String itemName;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String itemSeq;
	@Column
	private String openDe;
	@Column
	private String seQesitm;
	@Column
	private String updateDe;
	@Column
	private String useMethodQesitm;


}