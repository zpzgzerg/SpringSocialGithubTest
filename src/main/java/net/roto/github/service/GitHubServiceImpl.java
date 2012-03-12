package net.roto.github.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.github.api.GitHub;
import org.springframework.social.github.api.GitHubUser;
import org.springframework.social.github.api.GitHubUserProfile;
import org.springframework.social.github.api.impl.GitHubTemplate;
import org.springframework.stereotype.Service;

@Service("gitHubService")
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

	public List<GitHubUser> getFollowerList(String user) {
		return getAPI().userOperations().getFollowers(user);
	}

	public List<GitHubUser> getFollowingList(String user) {
		return getAPI().userOperations().getFollowing(user);
	}

	public GitHubUserProfile getUserProfile() {
		return getAPI().userOperations().getUserProfile();
	}

}
