package com.task.SocialMedia.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private String resourceName;
    private String field;
    private String fieldName;
    private int fieldId;
}