package it.uniroma3.siw.museo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.museo.controller.validator.CuratoreValidator;
import it.uniroma3.siw.museo.model.Curatore;
import it.uniroma3.siw.museo.model.Opera;
import it.uniroma3.siw.museo.service.ArtistaService;
import it.uniroma3.siw.museo.service.CuratoreService;
import it.uniroma3.siw.museo.service.OperaService;

@Controller
public class CuratoreController {
	
	
	
	@Autowired
	private CuratoreService curatoreService;
	@Autowired
	private CuratoreValidator curatoreValidator;
	
	@RequestMapping(value="/curatore", method = RequestMethod.GET)
    public String getCuratore(Model model) {
    	model.addAttribute("curatori", this.curatoreService.tutti());
        return "curatore/curatori";
    }
	
	@RequestMapping(value="/curatoreA", method = RequestMethod.GET)
    public String getCuratoreA(Model model) {
    	model.addAttribute("curatori", this.curatoreService.tutti());
        return "curatore/curatoriA";
    }
	
	@RequestMapping(value = "/curatore/{id}", method = RequestMethod.GET)
    public String getCuratore(@PathVariable("id") Long id, Model model) {
		Curatore curatore = this.curatoreService.curatorePerId(id);
		model.addAttribute("collezioni", curatore.getListaCollezioni());
    	model.addAttribute("curatore", curatore);
    	return "curatore/curatore";
    }
	
	@RequestMapping(value = "/curatoreA/{id}", method = RequestMethod.GET)
    public String getCuratoreA(@PathVariable("id") Long id, Model model) {
		Curatore curatore = this.curatoreService.curatorePerId(id);
		model.addAttribute("collezioni", curatore.getListaCollezioni());
    	model.addAttribute("curatore", curatore);
    	return "curatore/curatoreA";
    }
    
    @RequestMapping(value="/curatoreForm", method = RequestMethod.GET)
    public String getForm(Model model) {
    	model.addAttribute("curatore", new Curatore());
        return "curatore/curatoreForm.html";
    }
    
    @RequestMapping(value = "/curatoreForm", method = RequestMethod.POST)
    public String newCuratore(@ModelAttribute("curatore") Curatore curatore, 
    									Model model, BindingResult bindingResult) {
    	this.curatoreValidator.validate(curatore, bindingResult);
	    if (!bindingResult.hasErrors()) {
        	this.curatoreService.inserisci(curatore);
            model.addAttribute("curatori", this.curatoreService.tutti());
            return "admin/home";
        }
	   return "curatore/curatoreForm";   
    }
}
