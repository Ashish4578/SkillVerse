package com.skillverse.userservice.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillverse.userservice.dto.AppUserResponseDTO;
import com.skillverse.userservice.repository.SkillVerseUserRepository;
import com.skillverse.userservice.service.AdminProfileService;
import com.skillverse.userservice.service.GeneralService;

@Service
public class AdminProfileServiceImpl implements AdminProfileService {
	
	@Autowired
	private GeneralService generalService;

	@Autowired
	private SkillVerseUserRepository skillVerseUserRepository;
	

	@Override
	public String deleteUserProfile(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteCreatorProfile(Long creatorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppUserResponseDTO> getAllUsersProfile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppUserResponseDTO> getAllUsersByCreator(Long creatorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<AppUserResponseDTO, List<AppUserResponseDTO>> getAllCreatorsWithUsers() {
		// TODO Auto-generated method stub
		return null;
	}

}
