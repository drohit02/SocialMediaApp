package com.task.SocialMedia.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.SocialMedia.customexception.APIException;
import com.task.SocialMedia.customexception.ResourceNotFoundException;
import com.task.SocialMedia.dto.UserDTO;
import com.task.SocialMedia.models.User;
import com.task.SocialMedia.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new APIException("No users are present");
        }
        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    public UserDTO findUserById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return modelMapper.map(user, UserDTO.class);
    }

    public UserDTO createUser(UserDTO userDTO) {
        Optional<User> existingUser = userRepository.findByUserName(userDTO.getUserName());
        if (existingUser.isPresent()) {
            throw new APIException("User already present with username: " + userDTO.getUserName());
        }

        User user = modelMapper.map(userDTO, User.class);
        user = userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    public UserDTO updateUser(int id, UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        user.setUserName(userDTO.getUserName());
        // update other fields as necessary
        user = userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }

    public void deleteUser(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        userRepository.delete(user);
    }
}
