package org.quantum.usm.mapper.subscription;

import java.util.LinkedList;

import org.quantum.usm.dto.SubscriptionDto;
import org.quantum.usm.entity.Subscription;
import org.quantum.usm.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionMapper implements Mapper<SubscriptionDto, Subscription> {

	@Override
	public SubscriptionDto toDto(Subscription entity) {
		return new SubscriptionDto(entity.getId(), entity.getTitle());
	}

	@Override
	public Subscription toEntity(SubscriptionDto dto) {
		throw new UnsupportedOperationException("Method is not implemented");
	}

	public Iterable<SubscriptionDto> toDtos(Iterable<Subscription> entities) {
		LinkedList<SubscriptionDto> result = new LinkedList<>();
		for (Subscription subscription : entities) {
			result.add(toDto(subscription));
		}
		return result;
	}

}
