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

@Data
@Entity
@Table(name = "document")
@NoArgsConstructor
public class Document {
	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "DOCUMENT_SEQ_GENERATOR")
	@SequenceGenerator(name = "DOCUMENT_SEQ_GENERATOR", sequenceName = "DOCUMENT_SEQ", allocationSize = 10)
	private Long id;

	@Column(name = "title")
	private String title;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "path")
	private String path;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "stage_id")
	private Stage stage;

}
