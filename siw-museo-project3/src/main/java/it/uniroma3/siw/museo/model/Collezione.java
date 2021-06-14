package it.uniroma3.siw.museo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Data;


@Entity
public @Data class Collezione {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	 @Column(nullable = false)
	private String nome;
	 @Column(nullable = false)
	private String descrizione;
     @ManyToOne
	private Curatore curatore;
	 @OneToMany
	private List<Opera> opera;
}
