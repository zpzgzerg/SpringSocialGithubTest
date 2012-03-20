package net.roto.github.service;

import java.util.List;

import net.roto.github.model.User;

public interface SocialService<SocialType> {
	public SocialType getAPI();
	public User getUserProfile();
	public List<User> getFollowerList();
	public List<User> getFollowingList();
	
}
