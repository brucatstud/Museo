package it.uniroma3.siw.museo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.museo.model.Opera;
import it.uniroma3.siw.museo.repository.OperaRepository;


@Service
public class OperaService {
	
	@Autowired
	private OperaRepository operaRepository; 
	
	@Transactional
	public Opera inserisci(Opera opera) {
		return operaRepository.save(opera);
	}
	
	@Transactional
	public void elimina(Opera opera) {
		operaRepository.delete(opera);;
	}
	
	@Transactional
	public List<Opera> operaPerTitoloAndAnno(String titolo, int anno) {
		return operaRepository.findByTitoloAndAnno(titolo, anno);
	}

	@Transactional
	public List<Opera> tutti() {
		return (List<Opera>) operaRepository.findAll();
	}

	@Transactional
	public Opera operaPerId(Long id) {
		Optional<Opera> optional = operaRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExists(Opera opera) {
		List<Opera> artisti = this.operaRepository.findByTitoloAndAnno(opera.getTitolo(), opera.getAnno());
		if (artisti.size() > 0)
			return true;
		else 
			return false;
	}

	
	@Transactional
	public void update(String titolo, int anno, String descrizione, String path, Long id) {
		this.operaRepository.setOperaInfoById(titolo, anno, descrizione, path, id);
	}

}
