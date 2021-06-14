package it.uniroma3.siw.museo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Data;



@Entity
public @Data class Opera {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String titolo;
	
	@Column(nullable = false)
	private int anno;
	
	@Column(nullable = false)
	private String descrizione;
	
	@Column(nullable = false)
	private String path;
		
	@ManyToOne
	private Artista artista;
	
	@ManyToOne
	private Collezione collezione;
}
