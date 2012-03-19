package net.roto.github.model;

import java.util.List;

public class User {
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProfileImageUrl() {
		return profileImageUrl;
	}
	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}
	public List<User> getFollowerList() {
		return followerList;
	}
	public void setFollowerList(List<User> followerList) {
		this.followerList = followerList;
	}
	public List<User> getFolloweringList() {
		return followeringList;
	}
	public void setFolloweringList(List<User> followeringList) {
		this.followeringList = followeringList;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	private String id;
	private String name;
	private String email;
	private String profileImageUrl;
	private List<User> followerList;
	private List<User> followeringList;
}
