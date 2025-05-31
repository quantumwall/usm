package org.quantum.usm.dto;

import java.time.LocalDate;

public record ReadUserSubscriptionDto(Long subscriptionId, Long userId, String title, LocalDate expiration) {

}
