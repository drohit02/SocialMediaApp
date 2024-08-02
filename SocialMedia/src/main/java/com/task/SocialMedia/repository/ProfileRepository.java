package com.task.SocialMedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.SocialMedia.models.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer>{

}
