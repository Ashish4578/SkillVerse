package com.skillverse.userservice.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skillverse.userservice.dto.AppUserResponseDTO;
import com.skillverse.userservice.service.GeneralService;

@Service
public class GeneralServiceImpl implements GeneralService {

	@Override
	public Optional<Long> findByProfileIdByUserName(String username) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Long> findProfileIdByContactNumber(String contactNumber) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Long> findProfileIdByEmail(String email) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public AppUserResponseDTO findProfileById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppUserResponseDTO findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppUserResponseDTO findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppUserResponseDTO findByContact(String contactNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsByUsername(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String sendMailByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String sendMailByUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppUserResponseDTO authenticate(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
