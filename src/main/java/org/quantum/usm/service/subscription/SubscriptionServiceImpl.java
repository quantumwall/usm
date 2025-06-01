package org.quantum.usm.service.subscription;

import org.quantum.usm.entity.Subscription;
import org.quantum.usm.exception.EntityNotFoundException;
import org.quantum.usm.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

	private final SubscriptionRepository subscriptionRepository;

	public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository) {
		this.subscriptionRepository = subscriptionRepository;
	}

	@Override
	public Iterable<Subscription> getAvailableSubscriptions() {
		return subscriptionRepository.findAll();
	}

	@Override
	public Subscription getById(Integer id) {
		return subscriptionRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Subscription %d not found".formatted(id)));
	}

	@Override
	public Iterable<Subscription> getTopThreeSubscriptions() {
		return subscriptionRepository.findTopThreeSubscriptions();
	}

}
