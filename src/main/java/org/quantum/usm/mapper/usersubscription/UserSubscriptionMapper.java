package org.quantum.usm.mapper.usersubscription;

import java.util.LinkedList;

import org.quantum.usm.dto.ReadUserSubscriptionDto;
import org.quantum.usm.dto.UserSubscriptionDto;
import org.quantum.usm.entity.UserSubscription;
import org.quantum.usm.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class UserSubscriptionMapper implements Mapper<UserSubscriptionDto, UserSubscription> {

	@Override
	public UserSubscriptionDto toDto(UserSubscription entity) {
		return new UserSubscriptionDto(
				entity.getSubscription().getId(),
				entity.getExpirationDate());
	}

	@Override
	public UserSubscription toEntity(UserSubscriptionDto dto) {
		throw new UnsupportedOperationException("Method is not implemented");
	}

	public ReadUserSubscriptionDto toReadDto(UserSubscription subscription) {
		return new ReadUserSubscriptionDto(
				subscription.getId(),
				subscription.getUser().getId(),
				subscription.getSubscription().getTitle(),
				subscription.getExpirationDate());
	}

	public Iterable<ReadUserSubscriptionDto> toReadDtos(Iterable<UserSubscription> subscriptions) {
		LinkedList<ReadUserSubscriptionDto> result = new LinkedList<>();
		for (UserSubscription subscription : subscriptions) {
			result.add(toReadDto(subscription));
		}
		return result;
	}
}
