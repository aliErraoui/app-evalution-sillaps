package com.tweets.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.tweets.demo.service.ConnectionService;






@Controller
public class TweetsController {
	@Autowired
	ConnectionService connectionService;
	
	
	 @RequestMapping("/")
	    public String greeting( String name, Model model) {
		 String msg="";
			model.addAttribute("msg",msg);
         return "greeting";
	    }
	 
	 
	 @RequestMapping("/getTweets")
	    public String getTweetsSms(@ModelAttribute("countryCode") String countryCode,
	    		@ModelAttribute("gsm") String gsm,
	    		@ModelAttribute("motCle") String motCle,
	    		Model model) {
	    
		 String msg="Envoi en cours ..";
			model.addAttribute("msg",msg);

		 connectionService.getTweetsByKeyWord(gsm, motCle,countryCode);
		 
		 
	        return "greeting";
	    }
	 @RequestMapping("/home")
	    public String greetingHome(Model model ) {
		
		 connectionService.stpStream();
		String msg="";
		model.addAttribute(msg);
		 
        return "greeting";
	    }

}
