package it.uniroma3.siw.museo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.museo.controller.validator.OperaValidator;
import it.uniroma3.siw.museo.model.Artista;
import it.uniroma3.siw.museo.model.Collezione;
import it.uniroma3.siw.museo.model.Credentials;
import it.uniroma3.siw.museo.model.Curatore;
import it.uniroma3.siw.museo.model.Opera;
import it.uniroma3.siw.museo.service.ArtistaService;
import it.uniroma3.siw.museo.service.CollezioneService;
import it.uniroma3.siw.museo.service.OperaService;

@Controller
public class OperaController {
	
	
	
	@Autowired
	private OperaService operaService;
	@Autowired
	private ArtistaService artistaService;
	@Autowired
	private OperaValidator operaValidator;
	@Autowired
	private CollezioneService collezioneService;
	
    
    @RequestMapping(value="/operaForm", method = RequestMethod.GET)
    public String getForm(Model model) {
    	model.addAttribute("opera", new Opera());
        return "opera/operaForm";
    }
    
    @RequestMapping(value="/opera", method = RequestMethod.GET)
    public String getOpere(Model model) {
    	model.addAttribute("opere", this.operaService.tutti());
        return "opera/opere";
    }
    
    @RequestMapping(value="/operaA", method = RequestMethod.GET)
    public String getOpereA(Model model) {
    	model.addAttribute("opere", this.operaService.tutti());
        return "opera/opereA";
    }
    
    @RequestMapping(value="/elOpere", method = RequestMethod.GET)
    public String getOpereEl(Model model) {
    	model.addAttribute("opere", this.operaService.tutti());
        return "opera/elOpere";
    }
    
    @RequestMapping(value = "/elOpera/{id}", method = RequestMethod.GET)
    public String getOperaEl(@PathVariable("id") Long id, Model model) {
    	Opera opera = this.operaService.operaPerId(id);
    	model.addAttribute("opera", opera);
    	model.addAttribute("artista", opera.getArtista());
    	model.addAttribute("collezione", opera.getCollezione());
    	return "opera/elOpera";
    }
    @RequestMapping(value = "/operaDel/{oid}/{aid}/{cid}", method = RequestMethod.GET)
    public String delOpera(@PathVariable("oid") Long oid, @PathVariable("aid") Long aid, @PathVariable("cid") Long cid, Model model) {
    	Opera opera = operaService.operaPerId(oid);
    	Artista artista = artistaService.artistaPerId(aid);
    	artista.getListaOpere().remove(opera);
    	artistaService.inserisci(artista);
    	Collezione collezione = collezioneService.collezionePerId(cid);
    	collezione.getOpera().remove(opera);
    	collezioneService.inserisci(collezione);
    	operaService.elimina(opera);
    	return "admin/home";
    }
    
    @RequestMapping(value="/operaFormArtista/{aid}/{oid}", method = RequestMethod.GET)
    public String getListaCuratori(@PathVariable("aid") Long aid, @PathVariable("oid") Long oid,Model model) {
    	Opera opera = operaService.operaPerId(oid);
    	Artista artista = artistaService.artistaPerId(aid);
    	opera.setArtista(artista);
    	artista.getListaOpere().add(opera);
    	operaService.inserisci(opera);
    	artistaService.inserisci(artista);
    	model.addAttribute("opera", opera);
    	model.addAttribute("collezioni", this.collezioneService.tutti());
        return "opera/listaCollezioni";
    }
    
    @RequestMapping(value="/operaFormCollezione/{cid}/{oid}", method = RequestMethod.GET)
    public String getListaCollezione(@PathVariable("cid") Long cid, @PathVariable("oid") Long oid,Model model) {
    	Opera opera = operaService.operaPerId(oid);
    	Collezione collezione = this.collezioneService.collezionePerId(cid);
    	opera.setCollezione(collezione);
    	collezione.getOpera().add(opera);
    	operaService.inserisci(opera);
    	this.collezioneService.inserisci(collezione);
        return "admin/home";
    }
    

    @RequestMapping(value = "/opera/{id}", method = RequestMethod.GET)
    public String getOpera(@PathVariable("id") Long id, Model model) {
    	Opera opera = this.operaService.operaPerId(id);
    	model.addAttribute("opera", opera);
    	model.addAttribute("artista", opera.getArtista());
    	model.addAttribute("collezione", opera.getCollezione());
    	return "opera/opera";
    }
    
    @RequestMapping(value = "/operaA/{id}", method = RequestMethod.GET)
    public String getOperaA(@PathVariable("id") Long id, Model model) {
    	Opera opera = this.operaService.operaPerId(id);
    	model.addAttribute("opera", opera);
    	model.addAttribute("artista", opera.getArtista());
    	model.addAttribute("collezione", opera.getCollezione());
    	return "opera/operaA";
    }
    
    @RequestMapping(value = "/operaForm", method = RequestMethod.POST)
    public String newOpera(@ModelAttribute("opera") Opera opera, 
    									Model model, BindingResult bindingResult) {
    	this.operaValidator.validate(opera, bindingResult);
        if (!bindingResult.hasErrors()) {
        	this.operaService.inserisci(opera);
        	model.addAttribute("artisti",this.artistaService.tutti());
            return "opera/listaArtisti";
        }
        return "opera/operaForm";
}
    
    @RequestMapping(value = "/noArtista/{id}", method = RequestMethod.GET)
    public String noArtista(@PathVariable("id") Long id, Model model) {
    	Opera opera = this.operaService.operaPerId(id);
    	operaService.elimina(opera);
    	return "admin/home";
    }
    
    @RequestMapping(value = "/noCollezione/{id}", method = RequestMethod.GET)
    public String noCollezione(@PathVariable("id") Long id, Model model) {
    	Opera opera = this.operaService.operaPerId(id);
    	Artista artista=opera.getArtista();
    	artista.getListaOpere().remove(opera);
    	operaService.elimina(opera);
    	return "admin/home";
    }
    
}
