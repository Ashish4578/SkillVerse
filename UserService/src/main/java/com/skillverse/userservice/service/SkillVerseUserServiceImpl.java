package com.skillverse.userservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.skillverse.userservice.dto.AppUserResponseDTO;
import com.skillverse.userservice.entity.AppUser;
import com.skillverse.userservice.entity.Role;
import com.skillverse.userservice.mapper.UserServiceMapper;
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
	public AppUserResponseDTO findUserByItsId(Long id) {
		AppUser appUser = skillVerseUserRepository.findById(id).get();
		return UserServiceMapper.getConvertAppUserToResponse(appUser);
	}

	@Override
	public AppUserResponseDTO findProfileByUserName(String username) {
		List<AppUser> userResponseWhole = skillVerseUserRepository.findAll();
		AppUser appUser = userResponseWhole.stream().filter(appuser -> appuser.getUsername().contentEquals(username))
				.findAny().orElse(null);
		AppUserResponseDTO userResponse = new AppUserResponseDTO(appUser.getUsername(), appUser.getEmail(),
				appUser.getContactNumber(), appUser.isEnabled(), appUser.getRoles());
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
	public void deleteProfile(Long userId) {
		skillVerseUserRepository.deleteById(userId);
	}

	@Override
	public Optional<Long> findProfileIdByUserName(AppUser appUser) {
		return skillVerseUserRepository.findAppUserIdByUsername(appUser.getUsername());
	}

	@Override
	public AppUserResponseDTO authenticate(String username, String password, Role role) {

		return null;
	}

	@Override
	public Page<AppUserResponseDTO> getUsers(Pageable pageable) {

		return null;
	}

	@Override
	public void assignRoleToUser(Long userId, Role role) {

	}

	@Override
	public void removeRoleFromUser(Long userId, Role role) {

	}

	@Override
	public boolean existsByUsername(String username) {

		return false;
	}

	@Override
	public List<AppUserResponseDTO> getAllUsersByRole(Role role) {
		List<AppUser> users = skillVerseUserRepository.findAllByRole(role);
		return users.stream().map(UserServiceMapper::getConvertAppUserToResponse).collect(Collectors.toList());
	}

}
