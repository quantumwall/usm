package org.quantum.usm.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record UserSubscriptionDto(
		@NotNull(message = "Subscription id must be not null")
		Integer subscriptionId,
		@NotNull(message = "Expiration date must be not null")
		@Future(message = "Expiration date must be future")
		@JsonFormat(pattern = "yyyy-MM-dd")
		LocalDate expirationDate) {

}
