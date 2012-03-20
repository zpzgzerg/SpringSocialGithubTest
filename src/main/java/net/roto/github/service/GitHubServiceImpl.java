package net.roto.github.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import net.roto.github.model.User;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.github.api.GitHub;
import org.springframework.social.github.api.GitHubUser;
import org.springframework.social.github.api.GitHubUserProfile;
import org.springframework.social.github.api.impl.GitHubTemplate;
import org.springframework.stereotype.Service;

@Service("githubService")
public class GitHubServiceImpl implements GitHubService{	
	@Inject
	ConnectionRepository connectionRepository;
	
	public GitHub getAPI() {
		
		Connection<GitHub> gitHubConnection = connectionRepository.findPrimaryConnection(GitHub.class);
		if( gitHubConnection != null ){
			return gitHubConnection.getApi();
		}
		else{
			return new GitHubTemplate();
		}
	}
	@Override
	public User getUserProfile() {
		GitHubUserProfile gitHubUserProfile = getAPI().userOperations().getUserProfile();
		if( gitHubUserProfile != null ){
			User user = new User();
			user.setProfileImageUrl( gitHubUserProfile.getProfileImageUrl() );
			user.setId( Long.toString( gitHubUserProfile.getId() ) );
			user.setName( gitHubUserProfile.getName() );
			user.setEmail( gitHubUserProfile.getEmail() );
			user.setFollowerList( getFollowerList());
			return user;
		}else{
			throw new NullPointerException("GitHub 사용자 프로필이 존재하지 않습니다.");
		}
	}

	@Override
	public List<User> getFollowerList() {
		String username = getAPI().userOperations().getUserProfile().getUsername();
		List<GitHubUser> gitHubUserList = getAPI().userOperations().getFollowers(username);
		return convertToUserFromGitHubUser (gitHubUserList);
	}
	
	@Override
	public List<User> getFollowingList() {
		String username = getAPI().userOperations().getUserProfile().getUsername();
		List<GitHubUser> gitHubUserList = getAPI().userOperations().getFollowing(username);
		return convertToUserFromGitHubUser (gitHubUserList);
	}

	private List<User> convertToUserFromGitHubUser(List<GitHubUser> gitHubUserList){
		System.out.println("userList Size : " + gitHubUserList.size());
		List<User> userList = new ArrayList<User>();
		for(GitHubUser gitHubUser : gitHubUserList){
			User user = new User();
			user.setId( Long.toString( gitHubUser.getId() ) );
			user.setName( gitHubUser.getName() );
			user.setProfileImageUrl( gitHubUser.getAvatarUrl() );
			userList.add( user );			
		}
		return userList;
	}
}
