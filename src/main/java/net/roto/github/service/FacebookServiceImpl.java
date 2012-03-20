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

	@Override
	public List<User> getFollowerList() {
		long startTime = new Date().getTime();		
		List<FacebookProfile> facebookFriendList = getAPI().friendOperations().getFriendProfiles();
		System.out.println( "ģ����� ���ſ� �ɸ� : " + (new Date().getTime() - startTime) + "ms");
		List<User> convertedFriendList = new ArrayList<User>();
		for(FacebookProfile facebookProfile : facebookFriendList){
			User facebookFriend = convertFacebookProfileToUser(facebookProfile);
			convertedFriendList.add( facebookFriend );
		}
		
		return convertedFriendList;
	}

	@Override
	public List<User> getFollowingList() {
		return getFollowerList();
	}
	
	@Override
	public User getUserProfile() {
		FacebookProfile facebookProfile = getAPI().userOperations().getUserProfile();
		if( facebookProfile != null ){
			User user = convertFacebookProfileToUser(facebookProfile);
			user.setFollowerList( getFollowerList());
			return user;
		}else{
			throw new NullPointerException("Facebook ������ ������ �ùٸ��� �ʽ��ϴ�.");
		}
	}		
	
	private User convertFacebookProfileToUser(FacebookProfile facebookProfile){
		User user = new User();		
		user.setProfileImageUrl( createProfileUmageUrl(facebookProfile.getId()) );
		user.setName( facebookProfile.getName() );
		user.setEmail( facebookProfile.getEmail() );
		user.setId( facebookProfile.getId() );
		return user;
	}
	
	private String createProfileUmageUrl(String facebookId){
		return String.format("https://graph.facebook.com/%s/picture", facebookId);
	}
}
