package net.roto.github.controller;

import java.util.Map;

import javax.inject.Inject;

import net.roto.github.model.User;
import net.roto.github.service.SocialService;

import org.springframework.social.ApiBinding;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Inject
	Map<String, SocialService<ApiBinding>> socialServiceMap;
	
	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public String main(){
		return "main";
	}
	@RequestMapping(value = "/{socialType}", method = RequestMethod.GET)
	public String socialInfo(@PathVariable String socialType, Model model){
		SocialService<ApiBinding> socialService = socialServiceMap.get(socialType + "Service");	
		if( socialService.getAPI().isAuthorized() ){
			User user = socialService.getUserProfile();
			model.addAttribute("user", user);		
		}
		
		return "socialInfo";
	}
	
}
