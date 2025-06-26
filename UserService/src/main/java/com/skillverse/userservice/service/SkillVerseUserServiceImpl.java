package com.skillverse.userservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillverse.userservice.entity.AppUser;
import com.skillverse.userservice.entity.UserResponse;
import com.skillverse.userservice.repository.SkillVerseUserRepository;

@Service
public class SkillVerseUserServiceImpl implements SkillVerseUserService {

	@Autowired
	private SkillVerseUserRepository skillVerseUserRepository;

	@Override
	public AppUser createProfile(AppUser user) {
		return skillVerseUserRepository.save(user);
	}

	@Override
	public UserResponse findUserByItsId(Long id) {
		AppUser appUser = skillVerseUserRepository.findById(id).get();
		UserResponse userResponse = new UserResponse();
		userResponse.setUsername(appUser.getUsername());
		userResponse.setRoles(appUser.getRoles());
		return userResponse;
	}

	@Override
	public UserResponse findProfileByUserName(String username) {
		List<AppUser> userResponseWhole = skillVerseUserRepository.findAll();
		AppUser appUser = userResponseWhole.stream().filter(appuser -> appuser.getUsername().contentEquals(username))
				.findAny().orElse(null);
		UserResponse userResponse = new UserResponse(appUser.getUsername(), appUser.getRoles());
		return userResponse;
	}

	@Override
	public AppUser updateProfileData(AppUser updatedUser) {
		AppUser existingUser = skillVerseUserRepository.findById(updatedUser.getAppUserId()).get();
		existingUser.setUsername(updatedUser.getUsername());
		existingUser.setPassword(updatedUser.getPassword());
		existingUser.setEnabled(true);
		existingUser.setEmail(updatedUser.getEmail());
		existingUser.setContactNumber(updatedUser.getContactNumber());
		existingUser.setRoles(updatedUser.getRoles());
		skillVerseUserRepository.save(existingUser);
		return existingUser;
	}

	@Override
	public void deleteProfile(AppUser deleteUser) {
		skillVerseUserRepository.deleteById(deleteUser.getAppUserId());
	}

	@Override
	public Long findProfileIdByUserName(AppUser appUser) {
		List<AppUser> userResponseWhole = skillVerseUserRepository.findAll();
		AppUser existingUser = userResponseWhole.stream()
				.filter(appuser -> appuser.getUsername().contentEquals(appUser.getUsername())).findAny().orElse(null);
		return existingUser.getAppUserId();
	}

//	List<GrantedAuthority> authorities = new ArrayList<>();
//
//	for (AppRole role : user.getRoles()) {
//	    authorities.add(new SimpleGrantedAuthority(role.getName().name())); // e.g. ROLE_ADMIN
//
//	    role.getName().getPermissions().forEach(permission ->
//	        authorities.add(new SimpleGrantedAuthority(permission.name())) // e.g. DELETE_COURSE
//	    );
//	}

}
