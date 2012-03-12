package net.roto.github.controller;

import java.util.Locale;

import javax.inject.Inject;

import net.roto.github.service.GitHubService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Inject
	GitHubService gitHubService;
	
	//private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		if( gitHubService.getAPI().isAuthorized() ){
			model.addAttribute("userProfile", gitHubService.getUserProfile());		
			model.addAttribute("followers", gitHubService.getFollowerList("rotoshine"));
		}
		return "home";
	}
	
}
