package it.uniroma3.siw.museo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.museo.model.Opera;


public interface OperaRepository extends CrudRepository<Opera, Long> {


	

	public List<Opera> findByTitoloAndAnno(String titolo, int anno);
	
	@Modifying
	@Query("update Opera o set o.titolo = ?1, o.anno = ?2 , o.descrizione = ?3, o.path = ?4 where o.id = ?5")
	public void setOperaInfoById(String titolo,int anno, String descrizione, String path, Long userId);


}
