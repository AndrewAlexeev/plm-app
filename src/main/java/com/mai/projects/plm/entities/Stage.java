package com.mai.projects.plm.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "stage")
@NoArgsConstructor
public class Stage {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STAGE_SEQ_GENERATOR")
	@SequenceGenerator(name = "STAGE_SEQ_GENERATOR", sequenceName = "STAGE_SEQ", allocationSize = 10)
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "serialNumber")
	private Integer serialNumber;

	@Column(name = "startDate")
	private LocalDate startDate;

	@Column(name = "finishDate")
	private LocalDate finishDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product")
	private Product product;
}
