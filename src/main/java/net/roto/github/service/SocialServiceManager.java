package net.roto.github.service;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.social.ApiBinding;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.github.api.GitHub;

public class SocialServiceManager {
	@Inject
	Map<String, ? extends ApiBinding> socialServiceMap;
	
	public Class<? extends ApiBinding> getApiType(String socialType){
		if( socialType.equals("facebook") ){
			return Facebook.class;
		}else if(socialType.equals("github")){
			return GitHub.class;
		}else{
			return null;
		}
	}
}
