package com.task.SocialMedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.SocialMedia.models.SocialGroup;

@Repository
public interface SocialGroupRepository extends JpaRepository<SocialGroup, Integer>{

}
