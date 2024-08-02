package com.task.SocialMedia.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.SocialMedia.dto.SocialGroupDTO;
import com.task.SocialMedia.services.SocialGroupService;

@RestController
@RequestMapping("/api/social-groups")
public class SocialGroupController {

    @Autowired
    private SocialGroupService socialGroupService;

    @GetMapping
    public ResponseEntity<List<SocialGroupDTO>> getAllSocialGroups() {
        List<SocialGroupDTO> socialGroups = socialGroupService.getAllSocialGroups();
        return ResponseEntity.status(HttpStatus.OK).body(socialGroups);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SocialGroupDTO> getSocialGroupById(@PathVariable int id) {
        SocialGroupDTO socialGroupDTO = socialGroupService.findSocialGroupById(id);
        return ResponseEntity.status(HttpStatus.OK).body(socialGroupDTO);
    }

    @PostMapping
    public ResponseEntity<SocialGroupDTO> createSocialGroup(@RequestBody SocialGroupDTO socialGroupDTO) {
        SocialGroupDTO savedSocialGroupDTO = socialGroupService.createSocialGroup(socialGroupDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSocialGroupDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SocialGroupDTO> updateSocialGroup(@PathVariable int id,@RequestBody SocialGroupDTO socialGroupDTO) {
        SocialGroupDTO updatedSocialGroupDTO = socialGroupService.updateSocialGroup(id, socialGroupDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedSocialGroupDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSocialGroup(@PathVariable int id) {
        socialGroupService.deleteSocialGroup(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
