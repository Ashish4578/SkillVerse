package com.skillverse.userservice.service.impl;

import java.util.Optional;

import com.skillverse.userservice.entity.Course;
import com.skillverse.userservice.repository.SkillVerseUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillverse.userservice.dto.AppUserResponseDTO;
import com.skillverse.userservice.service.GeneralService;

@Service
public class GeneralServiceImpl implements GeneralService {

    @Autowired
    SkillVerseUserRepository skillVerseUserRepository;

    @Override
    public Optional<Course[]> getCourseList() {
        // 1. Create a few Course objects
        Course course1 = new Course();
        course1.setCourseId("CS101");
        course1.setCourseName("Introduction to Java");
        course1.setCoursePrice(49.99f);
        course1.setCourseCategory("CS101");
        course1.setCourseDescription("Introduction to Java");
        course1.setCourseImg("CS101");
        course1.setCourseCategory("Coding");
        course1.setCourseCreatorName("Ashihs");
        course1.setCourseCreatorPhone("9272373");
        course1.setCourseCreatorEmail("ashish@mail.com");

        Course course2 = new Course();
        course2.setCourseId("DB202");
        course2.setCourseName("Advanced SQL and Databases");
        course2.setCoursePrice(79.50f);
        course2.setCourseCategory("CS101");
        course2.setCourseDescription("Introduction to Pyhton");
        course2.setCourseImg("CS202");
        course2.setCourseCategory("Coding");
        course2.setCourseCreatorName("gadekar");
        course2.setCourseCreatorPhone("4272373");
        course2.setCourseCreatorEmail("gadekar@mail.com");

        // 2. Create an array of Course objects
        Course[] courseArray = {course1, course2};

        // 3. Wrap the array in an Optional and return it
        return Optional.of(courseArray);
    }
    @Override
	public Optional<Long> findByProfileIdByUserName(String username) {
		
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
