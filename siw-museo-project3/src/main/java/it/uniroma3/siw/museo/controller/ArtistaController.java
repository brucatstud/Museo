package it.uniroma3.siw.museo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.museo.controller.validator.ArtistaValidator;
import it.uniroma3.siw.museo.model.Artista;
import it.uniroma3.siw.museo.model.Opera;
import it.uniroma3.siw.museo.service.ArtistaService;
import it.uniroma3.siw.museo.service.OperaService;

@Controller
public class ArtistaController {
	
	
	
	@Autowired
	private ArtistaService artistaService;
	@Autowired
	private ArtistaValidator artistaValidator;
	
	@RequestMapping(value="/artista", method = RequestMethod.GET)
    public String getArtista(Model model) {
    	model.addAttribute("artisti", this.artistaService.tutti());
        return "artista/artisti";
    }
	
	@RequestMapping(value="/artistaA", method = RequestMethod.GET)
    public String getArtistaA(Model model) {
    	model.addAttribute("artisti", this.artistaService.tutti());
        return "artista/artistiA";
    }
	
	@RequestMapping(value = "/artista/{id}", method = RequestMethod.GET)
    public String getArtista(@PathVariable("id") Long id, Model model) {
		Artista artista = this.artistaService.artistaPerId(id);
		model.addAttribute("opere",artista.getListaOpere() );
    	model.addAttribute("artista", artista);
    	return "artista/artista";
    }
	
	@RequestMapping(value = "/artistaA/{id}", method = RequestMethod.GET)
    public String getArtistaA(@PathVariable("id") Long id, Model model) {
		Artista artista = this.artistaService.artistaPerId(id);
		model.addAttribute("opere",artista.getListaOpere() );
    	model.addAttribute("artista", artista);
    	return "artista/artistaA";
    }
    
    @RequestMapping(value="/artistaForm", method = RequestMethod.GET)
    public String getForm(Model model) {
    	model.addAttribute("artista", new Artista());
        return "artista/artistaForm";
    }
    
    @RequestMapping(value = "/artistaForm", method = RequestMethod.POST)
    public String newArtista(@ModelAttribute("artista") Artista artista, 
    									Model model, BindingResult bindingResult) {
    	    this.artistaValidator.validate(artista, bindingResult);
    	    if (!bindingResult.hasErrors()) {
        	this.artistaService.inserisci(artista);
            model.addAttribute("artisti", this.artistaService.tutti());
            return "admin/home";
        }
    	    return "artista/artistaForm";
    }
}
