package org.quantum.usm.mapper.subscription;

import java.util.LinkedList;

import org.quantum.usm.dto.ReadSubscriptionDto;
import org.quantum.usm.entity.Subscription;
import org.quantum.usm.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionMapper implements Mapper<ReadSubscriptionDto, Subscription> {

	@Override
	public ReadSubscriptionDto toDto(Subscription entity) {
		return new ReadSubscriptionDto(entity.getId(), entity.getTitle());
	}

	@Override
	public Subscription toEntity(ReadSubscriptionDto dto) {
		throw new UnsupportedOperationException("Method is not implemented");
	}

	public Iterable<ReadSubscriptionDto> toDtos(Iterable<Subscription> entities) {
		LinkedList<ReadSubscriptionDto> result = new LinkedList<>();
		for (Subscription subscription : entities) {
			result.add(toDto(subscription));
		}
		return result;
	}

}
