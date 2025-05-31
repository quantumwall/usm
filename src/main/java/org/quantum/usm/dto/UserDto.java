package org.quantum.usm.dto;

import org.quantum.usm.validation.OnCreate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserDto(
		Long id,
		@NotNull(message = "Username must be not null", groups = OnCreate.class)
		@Size(max = 255, message = "Username must be less than 255 characters")
		String username,
		String firstname,
		String lastname) {

}
