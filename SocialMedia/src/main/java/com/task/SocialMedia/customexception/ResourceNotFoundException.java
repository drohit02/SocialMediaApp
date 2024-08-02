package com.task.SocialMedia.customexception;

import lombok.Data;

@Data
public class ResourceNotFoundException extends RuntimeException {

	private String resourceName;
	private String field;
	private String fieldName;
	private String msg;
	private int fieldId;

	public ResourceNotFoundException(String resourceName, String field, String fieldName) {
		super(String.format("%s not found with %s : %s", resourceName, field, fieldName));
		this.resourceName = resourceName;
		this.field = field;
		this.fieldName = fieldName;
	}

	public ResourceNotFoundException(String resourceName, String field, int fieldId) {
		super(String.format("%s not found with %s : %d", resourceName, field, fieldId));
		this.resourceName = resourceName;
		this.field = field;
		this.fieldId = fieldId;
	}

	public ResourceNotFoundException(String msg) {
		super(msg);
		this.msg = msg;
	}
}
