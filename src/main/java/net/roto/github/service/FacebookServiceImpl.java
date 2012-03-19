package net.roto.github.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import net.roto.github.model.User;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Service;

@Service("facebookService")
public class FacebookServiceImpl implements FacebookService{
	@Inject
	ConnectionRepository connectionRepository;
	
	public Facebook getAPI() {
		Connection<Facebook> facebookConnection = connectionRepository.findPrimaryConnection(Facebook.class);
		if( facebookConnection != null ){
			return facebookConnection.getApi();
		}
		else{
			return new FacebookTemplate();
		}
	}

	public List<User> getFollowerList(String user) {
		long startTime = new Date().getTime();		
		List<FacebookProfile> facebookFriendList = getAPI().friendOperations().getFriendProfiles();
		System.out.println( "친구목록 수신에 걸린 : " + (new Date().getTime() - startTime) + "ms");
		List<User> convertedFriendList = new ArrayList<User>();
		for(FacebookProfile facebookProfile : facebookFriendList){
			User facebookFriend = new User();
			facebookFriend.setName( facebookProfile.getName() );
			facebookFriend.setProfileImageUrl(  "https://graph.facebook.com/" + facebookProfile.getId() + "/picture" );
			convertedFriendList.add( facebookFriend );
		}
		
		return convertedFriendList;
	}

	public List<User> getFollowingList(String user) {
		return getFollowerList(user);
	}

	public User getUserProfile() {
		FacebookProfile facebookProfile = getAPI().userOperations().getUserProfile();
		if( facebookProfile != null ){
			User user = new User();		
			user.setProfileImageUrl( "https://graph.facebook.com/" + facebookProfile.getId() + "/picture" );
			user.setName( facebookProfile.getName() );
			user.setEmail( facebookProfile.getEmail() );
			user.setId( facebookProfile.getId() );
			user.setFollowerList( getFollowerList(""));
			return user;
		}else{
			throw new NullPointerException("Facebook 프로필 정보가 올바르지 않습니다.");
		}
	}		
}
