package com.cos.mediAPI.home.mediModel;

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
	@Column(columnDefinition = "MEDIUMTEXT")
	private String atpnQesitm;
	@Column(columnDefinition = "MEDIUMTEXT")
	private String atpnWarnQesitm;
	@Column(columnDefinition = "MEDIUMTEXT")
	private String depositMethodQesitm;
	@Column(columnDefinition = "MEDIUMTEXT")
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
	@Column(columnDefinition = "BIGINT")
	private Long itemSeq;
	@Column
	private String openDe;
	@Column(columnDefinition = "MEDIUMTEXT")
	private String seQesitm;
	@Column
	private String updateDe;
	@Column
	private String useMethodQesitm;


}