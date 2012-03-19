package net.roto.github.service;

import java.util.ArrayList;
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
		List<FacebookProfile> facebookFriendList = getAPI().friendOperations().getFriendProfiles();
		System.out.println("친구 프로필 목록 : " + facebookFriendList.size());
		List<User> convertedFriendList = new ArrayList<User>();
		for(FacebookProfile facebookProfile : facebookFriendList){
			User facebookFriend = new User();
			facebookFriend.setName( facebookProfile.getName() );
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
			System.out.println( facebookProfile.toString() );
			User user = new User();
			user.setName( facebookProfile.getName() );
			user.setEmail( facebookProfile.getEmail() );
			user.setId( facebookProfile.getId() );
			
			return user;
		}else{
			throw new NullPointerException("Facebook 프로필 정보가 올바르지 않습니다.");
		}
	}

}
