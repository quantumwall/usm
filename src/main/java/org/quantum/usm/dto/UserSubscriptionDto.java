package org.quantum.usm.dto;

import jakarta.validation.constraints.NotNull;

public record UserSubscriptionDto(
		@NotNull(message = "User id must be not null")
		Long userId,
		@NotNull(message = "Subscription id must be not null")
		Integer subscriptionId,
		@NotNull(message = "Expiration date must be not null")
		String expirationDate) {

}
