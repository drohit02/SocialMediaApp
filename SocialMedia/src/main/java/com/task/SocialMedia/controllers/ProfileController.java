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

import com.task.SocialMedia.dto.ProfileDTO;
import com.task.SocialMedia.services.ProfileService;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping
    public ResponseEntity<List<ProfileDTO>> getAllProfiles() {
        List<ProfileDTO> profiles = profileService.getAllProfiles();
        return ResponseEntity.status(HttpStatus.OK).body(profiles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDTO> getProfileById(@PathVariable int id) {
        ProfileDTO profileDTO = profileService.findProfileById(id);
        return ResponseEntity.status(HttpStatus.OK).body(profileDTO);
    }

    @PostMapping
    public ResponseEntity<ProfileDTO> createProfile(@RequestBody ProfileDTO profileDTO) {
        ProfileDTO savedProfileDTO = profileService.createProfile(profileDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProfileDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileDTO> updateProfile(@PathVariable int id,@RequestBody ProfileDTO profileDTO) {
        ProfileDTO updatedProfileDTO = profileService.updateProfile(id, profileDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProfileDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable int id) {
        profileService.deleteProfile(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
