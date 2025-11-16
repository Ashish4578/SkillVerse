package com.skillverse.userservice.service.impl;

import java.util.List;

import com.skillverse.userservice.entity.AppUser;
import com.skillverse.userservice.entity.RegisterUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillverse.userservice.dto.AppUserRequestDTO;
import com.skillverse.userservice.dto.AppUserResponseDTO;
import com.skillverse.userservice.repository.SkillVerseUserRepository;
import com.skillverse.userservice.service.GeneralService;
import com.skillverse.userservice.service.SuperAdminProfileService;

import static com.skillverse.userservice.mapper.UserServiceMapper.getConvertAppUserRequestDTOToAppUser;
import static com.skillverse.userservice.mapper.UserServiceMapper.getConvertAppUserToResponse;

@Service
@Slf4j
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
    public AppUserResponseDTO createAdminProfile(AppUserRequestDTO dto) {
        AppUser appUser = skillVerseUserRepository.save(getConvertAppUserRequestDTOToAppUser(dto));
        return getConvertAppUserToResponse(appUser);

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

    @Override
    public void assignRoleToUser(Long userId, String roleName) {

    }

    @Override
    public void removeRoleFromUser(Long userId, String roleName) {

    }

    @Override
    public List<String> getUserRoles(Long userId) {
        return List.of();
    }

    @Override
    public void createOrUpdateRole(String roleName, List<String> permissions) {

    }

}
