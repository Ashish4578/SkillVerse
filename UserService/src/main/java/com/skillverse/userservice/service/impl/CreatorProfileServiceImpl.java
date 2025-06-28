package com.skillverse.userservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillverse.userservice.dto.AppUserRequestDTO;
import com.skillverse.userservice.dto.AppUserResponseDTO;
import com.skillverse.userservice.repository.SkillVerseUserRepository;
import com.skillverse.userservice.service.CreatorProfileService;
import com.skillverse.userservice.service.GeneralService;

@Service
public class CreatorProfileServiceImpl implements CreatorProfileService{

	@Autowired
	private GeneralService generalService;

	@Autowired
	private SkillVerseUserRepository skillVerseUserRepository;
	
	@Override
	public AppUserResponseDTO createCreatorProfile(AppUserRequestDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppUserResponseDTO updateCreatorProfile(AppUserRequestDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteCreatorProfile(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppUserResponseDTO> getEnrolledUsers(Long creatorId) {
		// TODO Auto-generated method stub
		return null;
	}

}
