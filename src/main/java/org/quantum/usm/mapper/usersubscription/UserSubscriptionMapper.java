package org.quantum.usm.mapper.usersubscription;

import java.time.format.DateTimeFormatter;

import org.quantum.usm.dto.UserSubscriptionDto;
import org.quantum.usm.entity.UserSubscription;
import org.quantum.usm.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class UserSubscriptionMapper implements Mapper<UserSubscriptionDto, UserSubscription> {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE;

	@Override
	public UserSubscriptionDto toDto(UserSubscription entity) {
		return new UserSubscriptionDto(
				entity.getUser().getId(),
				entity.getSubscription().getId(),
				FORMATTER.format(entity.getExpirationDate()));
	}

	@Override
	public UserSubscription toEntity(UserSubscriptionDto dto) {
		return null;
	}
}
