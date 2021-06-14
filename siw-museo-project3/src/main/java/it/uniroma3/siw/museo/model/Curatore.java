package it.uniroma3.siw.museo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;


@Entity
public @Data class Curatore {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	 @Column(nullable = false)
    private String nome;
	 @Column(nullable = false)
	private String cognome;
	 @Column(nullable = false)
	private Date dataDiNascita;
	 @Column(nullable = false)
	private String luogoDiNascita;
	 @Column(nullable = false)
	private String email;
	 @Column(nullable = false)
	private String numeroDiTelefono;
	 @Column(nullable = false)
	private Long matricola;
	 
	 @OneToMany
	 private List<Collezione> listaCollezioni;
	
}
