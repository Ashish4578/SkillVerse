package com.skillverse.userservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillverse.userservice.dto.AppUserRequestDTO;
import com.skillverse.userservice.dto.AppUserResponseDTO;
import com.skillverse.userservice.repository.SkillVerseUserRepository;
import com.skillverse.userservice.service.GeneralService;
import com.skillverse.userservice.service.SuperAdminProfileService;

@Service
public class SuperAdminProfileServiceImpl implements SuperAdminProfileService{

	@Autowired
	private GeneralService generalService;

	@Autowired
	private SkillVerseUserRepository skillVerseUserRepository;
	
	@Override
	public AppUserResponseDTO updateAnyProfile(AppUserRequestDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteAnyProfile(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public void createAdminProfile(AppUserRequestDTO dto) {

    }

    @Override
	public List<AppUserResponseDTO> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppUserResponseDTO> getAllCreators() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppUserResponseDTO> getAllAdmins() {
		// TODO Auto-generated method stub
		return null;
	}

}
