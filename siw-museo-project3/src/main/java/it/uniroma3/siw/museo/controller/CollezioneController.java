package it.uniroma3.siw.museo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.museo.controller.validator.CollezioneValidator;
import it.uniroma3.siw.museo.model.Artista;
import it.uniroma3.siw.museo.model.Collezione;
import it.uniroma3.siw.museo.model.Curatore;
import it.uniroma3.siw.museo.model.Opera;
import it.uniroma3.siw.museo.service.ArtistaService;
import it.uniroma3.siw.museo.service.CollezioneService;
import it.uniroma3.siw.museo.service.CuratoreService;
import it.uniroma3.siw.museo.service.OperaService;

@Controller
public class CollezioneController {
	
	
	
	@Autowired
	private CollezioneService collezioneService;
	
	@Autowired
	private CuratoreService curatoreService;

	@Autowired
	private CollezioneValidator collezioneValidator;

	
	@RequestMapping(value="/collezione", method = RequestMethod.GET)
    public String getCollezione(Model model) {
    	model.addAttribute("collezioni", this.collezioneService.tutti());
        return "collezione/collezioni";
    }
	
	@RequestMapping(value="/collezioneA", method = RequestMethod.GET)
    public String getCollezioneA(Model model) {
    	model.addAttribute("collezioni", this.collezioneService.tutti());
        return "collezione/collezioniA";
    }
	
	@RequestMapping(value = "/collezione/{id}", method = RequestMethod.GET)
    public String getCollezione(@PathVariable("id") Long id, Model model) {
		Collezione coll = this.collezioneService.collezionePerId(id);
		model.addAttribute("curatore", coll.getCuratore());
		model.addAttribute("opere", coll.getOpera());
    	model.addAttribute("collezione", coll);
    	return "collezione/collezione.html";
    }
	
	@RequestMapping(value = "/collezioneA/{id}", method = RequestMethod.GET)
    public String getCollezioneA(@PathVariable("id") Long id, Model model) {
		Collezione coll = this.collezioneService.collezionePerId(id);
		model.addAttribute("curatore", coll.getCuratore());
		model.addAttribute("opere", coll.getOpera());
    	model.addAttribute("collezione", coll);
    	return "collezione/collezioneA.html";
    }
	
	@RequestMapping(value="/elCollezioni", method = RequestMethod.GET)
    public String elCollezioni(Model model) {
    	model.addAttribute("collezioni", this.collezioneService.tutti());
        return "collezione/elCollezioni";
    }
	
	@RequestMapping(value = "/elCollezione/{id}", method = RequestMethod.GET)
    public String elCollezione(@PathVariable("id") Long id, Model model) {
		Collezione coll = this.collezioneService.collezionePerId(id);
		model.addAttribute("curatore", coll.getCuratore());
		model.addAttribute("opere", coll.getOpera());
    	model.addAttribute("collezione", coll);
    	return "collezione/elCollezione";
    }
	
	@RequestMapping(value="/collezioneDel/{id}", method = RequestMethod.GET)
    public String delCollezione(@PathVariable("id") Long id, Model model) {
    	Collezione collezione = collezioneService.collezionePerId(id);
    	collezione.getCuratore().getListaCollezioni().remove(collezione);
    	collezioneService.elimina(collezione);
        return "admin/home";
    }
    
    @RequestMapping(value="/collezioneFormCuratore/{cuid}/{coid}", method = RequestMethod.GET)
    public String getListaCuratori(@PathVariable("cuid") Long cuid, @PathVariable("coid") Long coid,Model model) {
    	Collezione collezione = collezioneService.collezionePerId(coid);
    	Curatore curatore = curatoreService.curatorePerId(cuid);
    	collezione.setCuratore(curatore);
    	curatore.getListaCollezioni().add(collezione);
    	collezioneService.inserisci(collezione);
        return "admin/home";
    }
    
    @RequestMapping(value = "/collezioneForm", method = RequestMethod.GET)
    public String getForm(Model model) {
    	Collezione collezione = new Collezione();
    	model.addAttribute("collezione", collezione);
    	return "collezione/collezioneForm.html";
    }
    
    @RequestMapping(value = "/collezioneForm", method = RequestMethod.POST)
    public String newCollezione(@ModelAttribute("collezione") Collezione collezione, 
    									Model model, BindingResult bindingResult) {
    	this.collezioneValidator.validate(collezione, bindingResult);
	    if (!bindingResult.hasErrors()) {
        	this.collezioneService.inserisci(collezione);
            model.addAttribute("curatori", curatoreService.tutti());
            return "collezione/listaCuratori";
        }
	    return "collezione/collezioneForm";
    }
    
    
    @RequestMapping(value="/noCuratore/{id}", method = RequestMethod.GET)
    public String noCuratore(@PathVariable("id") Long id, Model model) {
    	Collezione collezione = collezioneService.collezionePerId(id);
    	collezioneService.elimina(collezione);
        return "admin/home";
    }
    

}
