package org.quantum.usm.service.usersubscription;

import org.quantum.usm.entity.UserSubscription;
import org.quantum.usm.repository.UserSubscriptionRepository;
import org.springframework.stereotype.Service;

@Service
public class UserSubscriptionServiceImpl implements UserSubscriptionService {

	private final UserSubscriptionRepository userSubscriptionRepository;

	public UserSubscriptionServiceImpl(UserSubscriptionRepository userSubscriptionRepository) {
		this.userSubscriptionRepository = userSubscriptionRepository;
	}

	@Override
	public Iterable<UserSubscription> getUserSubscriptions(Long userId) {
		return userSubscriptionRepository.findByUserId(userId);
	}

	@Override
	public UserSubscription create(UserSubscription userSubscription) {
		return userSubscriptionRepository.save(userSubscription);
	}

	@Override
	public void delete(Long userSubscriptionId) {
		userSubscriptionRepository.deleteById(userSubscriptionId);
	}

}
