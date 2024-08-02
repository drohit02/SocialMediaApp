package com.task.SocialMedia.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.SocialMedia.customexception.APIException;
import com.task.SocialMedia.customexception.ResourceNotFoundException;
import com.task.SocialMedia.dto.ProfileDTO;
import com.task.SocialMedia.models.Profile;
import com.task.SocialMedia.repository.ProfileRepository;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ProfileDTO> getAllProfiles() {
        List<Profile> profiles = profileRepository.findAll();
        if (profiles.isEmpty()) {
            throw new APIException("No profiles are present");
        }
        return profiles.stream()
                .map(profile -> modelMapper.map(profile, ProfileDTO.class))
                .collect(Collectors.toList());
    }

    public ProfileDTO findProfileById(int id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile", "id", id));
        return modelMapper.map(profile, ProfileDTO.class);
    }

    public ProfileDTO createProfile(ProfileDTO profileDTO) {
        Profile profile = modelMapper.map(profileDTO, Profile.class);
        profile = profileRepository.save(profile);
        return modelMapper.map(profile, ProfileDTO.class);
    }

    public ProfileDTO updateProfile(int id, ProfileDTO profileDTO) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile", "id", id));

        profile.setUser(profileDTO.getUser()); // update necessary fields
        profile = profileRepository.save(profile);
        return modelMapper.map(profile, ProfileDTO.class);
    }

    public void deleteProfile(int id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile", "id", id));
        profileRepository.delete(profile);
    }
}
