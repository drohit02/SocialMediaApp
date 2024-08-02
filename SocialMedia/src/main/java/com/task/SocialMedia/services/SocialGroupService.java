package com.task.SocialMedia.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.SocialMedia.customexception.APIException;
import com.task.SocialMedia.customexception.ResourceNotFoundException;
import com.task.SocialMedia.dto.SocialGroupDTO;
import com.task.SocialMedia.models.SocialGroup;
import com.task.SocialMedia.repository.SocialGroupRepository;

@Service
public class SocialGroupService {

    @Autowired
    private SocialGroupRepository socialGroupRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<SocialGroupDTO> getAllSocialGroups() {
        List<SocialGroup> socialGroups = socialGroupRepository.findAll();
        if (socialGroups.isEmpty()) {
            throw new APIException("No social groups are present");
        }
        return socialGroups.stream()
                .map(socialGroup -> modelMapper.map(socialGroup, SocialGroupDTO.class))
                .collect(Collectors.toList());
    }

    public SocialGroupDTO findSocialGroupById(int id) {
        SocialGroup socialGroup = socialGroupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SocialGroup", "id", id));
        return modelMapper.map(socialGroup, SocialGroupDTO.class);
    }

    public SocialGroupDTO createSocialGroup(SocialGroupDTO socialGroupDTO) {
        SocialGroup socialGroup = modelMapper.map(socialGroupDTO, SocialGroup.class);
        socialGroup = socialGroupRepository.save(socialGroup);
        return modelMapper.map(socialGroup, SocialGroupDTO.class);
    }

    public SocialGroupDTO updateSocialGroup(int id, SocialGroupDTO socialGroupDTO) {
        SocialGroup socialGroup = socialGroupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SocialGroup", "id", id));

        socialGroup.setGroupName(socialGroupDTO.getGroupName()); // update necessary fields
        socialGroup = socialGroupRepository.save(socialGroup);
        return modelMapper.map(socialGroup, SocialGroupDTO.class);
    }

    public void deleteSocialGroup(int id) {
        SocialGroup socialGroup = socialGroupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SocialGroup", "id", id));
        socialGroupRepository.delete(socialGroup);
    }
}
