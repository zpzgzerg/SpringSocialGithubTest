package net.roto.github.service;

import java.util.List;

import org.springframework.social.github.api.GitHub;
import org.springframework.social.github.api.GitHubUser;
import org.springframework.social.github.api.GitHubUserProfile;

public interface GitHubService extends SocialService<GitHub>{
	public List<GitHubUser> getFollowerList(String user);
	public List<GitHubUser> getFollowingList(String user);
	public GitHubUserProfile getUserProfile();
}
