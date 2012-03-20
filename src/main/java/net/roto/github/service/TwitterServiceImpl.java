package net.roto.github.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import net.roto.github.model.User;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;

@Service("twitterService")
public class TwitterServiceImpl implements TwitterService{

	@Inject
	ConnectionRepository connectionRepository;
	
	@Override
	public Twitter getAPI() {
		Connection<Twitter> twitterConnection = connectionRepository.findPrimaryConnection(Twitter.class);
		if( twitterConnection != null ){
			return twitterConnection.getApi();
		}
		else{
			return new TwitterTemplate();
		}
	}

	@Override
	public User getUserProfile() {
		TwitterProfile twitterProfile = getAPI().userOperations().getUserProfile();
		User user = convertTwitterProfileToUser(twitterProfile);
		user.setFollowerList( getFollowerList());
		//user.setFolloweringList( getFollowingList());
		return user;
	}

	@Override
	public List<User> getFollowerList() {
		List<TwitterProfile> followerList = getAPI().friendOperations().getFollowers();
		return convertTwitterProfileListToUserList(followerList);
		
	}

	@Override
	public List<User> getFollowingList() {
		List<TwitterProfile> followingList = getAPI().friendOperations().getFriends();
		return convertTwitterProfileListToUserList(followingList);
	}
	
	private User convertTwitterProfileToUser(TwitterProfile twitterProfile){
		if( twitterProfile != null ){
			User user = new User();
			user.setId( Long.toString( twitterProfile.getId()) );
			user.setName( twitterProfile.getName() );
			user.setProfileImageUrl( twitterProfile.getProfileImageUrl() );
			
			return user;
		}else{
			throw new NullPointerException("트위터 프로필이 올바르지 않습니다.");
		}
	}
	
	private List<User> convertTwitterProfileListToUserList(List<TwitterProfile> twitterProfileList){
		List<User> userList = new ArrayList<User>();
		for(TwitterProfile twitterProfile : twitterProfileList){
			userList.add( convertTwitterProfileToUser(twitterProfile) );
		}
		return userList;
	}

}
