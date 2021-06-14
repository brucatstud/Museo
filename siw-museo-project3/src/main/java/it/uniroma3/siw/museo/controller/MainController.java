package it.uniroma3.siw.museo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class MainController {
	
    
    @RequestMapping(value={"/","/index"}, method = RequestMethod.GET)
    public String home(Model model) {
    	return "index.html";
    }
    
    @RequestMapping(value={"/home"}, method = RequestMethod.GET)
    public String homePostLogin(Model model) {
    	return "home.html";
    }
    
    @RequestMapping(value={"/info"}, method = RequestMethod.GET)
    public String info(Model model) {
    	return "informazioni.html";
    }
    
    @RequestMapping(value={"/infoA"}, method = RequestMethod.GET)
    public String infoA2(Model model) {
    	return "informazioniA.html";
    }
    
    @RequestMapping(value={"/homeA"}, method = RequestMethod.GET)
    public String homePostLoginAdmin(Model model) {
    	return "admin/home";
    }

}
