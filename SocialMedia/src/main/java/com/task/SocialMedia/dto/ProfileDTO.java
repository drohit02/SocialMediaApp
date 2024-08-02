package com.task.SocialMedia.dto;

import com.task.SocialMedia.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {
    private int profileId;
    private User user; // Use userId to link with UserDTO
}
