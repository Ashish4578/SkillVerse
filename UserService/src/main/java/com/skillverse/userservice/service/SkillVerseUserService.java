package com.skillverse.userservice.service;

import com.skillverse.userservice.entity.AppUser;
import com.skillverse.userservice.entity.UserResponse;

public interface SkillVerseUserService {
	
	Long findProfileIdByUserName(AppUser appUser);

	AppUser createProfile(AppUser user);
	
	UserResponse findUserByItsId(Long id);
	
	UserResponse findProfileByUserName(String username);
	
	AppUser updateProfileData(AppUser updatedUser);
	
	void deleteProfile(AppUser deleteUser);
}
