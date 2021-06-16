package it.uniroma3.siw.museo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import lombok.Data;

@Entity
public @Data class Artista {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	 @Column(nullable = false)
	private String nome;
	 @Column(nullable = false)
	private String cognome;
	 @Column(nullable = true)
	private Date dataDiNascita;
	 @Column(nullable = false)
	private String luogoDiNascita;
	 
	private Date dataDiMorte;
	 @Column
	private String luogoDiMorte;
	 @Column(nullable = false)
	private String nazionalita;
	 @OneToMany
	 @OrderBy("titolo asc")
	 private List<Opera> listaOpere;
	


}
