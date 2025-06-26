package com.skillverse.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillverse.userservice.entity.AppUser;

public interface SkillVerseUserRepository extends JpaRepository<AppUser, Long>{

}
