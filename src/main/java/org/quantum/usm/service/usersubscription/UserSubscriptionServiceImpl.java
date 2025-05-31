package org.quantum.usm.service.usersubscription;

import org.quantum.usm.dto.UserSubscriptionDto;
import org.quantum.usm.entity.Subscription;
import org.quantum.usm.entity.User;
import org.quantum.usm.entity.UserSubscription;
import org.quantum.usm.exception.EntityAlreadyExistsException;
import org.quantum.usm.exception.EntityNotFoundException;
import org.quantum.usm.repository.UserSubscriptionRepository;
import org.quantum.usm.service.subscription.SubscriptionService;
import org.quantum.usm.service.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserSubscriptionServiceImpl implements UserSubscriptionService {

	private final UserSubscriptionRepository userSubscriptionRepository;
	private final UserService userService;
	private final SubscriptionService subscriptionService;

	@Override
	public Iterable<UserSubscription> getUserSubscriptions(Long userId) {
		return userSubscriptionRepository.findByUserId(userId);
	}

	@Override
	@Transactional
	public UserSubscription create(Long userId, UserSubscriptionDto userSubscriptionDto) {
		User user = userService.get(userId);
		Subscription subscription = subscriptionService.getById(userSubscriptionDto.subscriptionId());
		if (userSubscriptionRepository.findByUserIdAndSubscriptionId(userId, userSubscriptionDto.subscriptionId())
				.isPresent()) {
			throw new EntityAlreadyExistsException(
					"User subscription %d already exists".formatted(userSubscriptionDto.subscriptionId()));
		}
		UserSubscription userSubscription = new UserSubscription();
		userSubscription.setUser(user);
		userSubscription.setSubscription(subscription);
		userSubscription.setExpirationDate(userSubscriptionDto.expirationDate());
		return userSubscriptionRepository.save(userSubscription);
	}

	@Override
	@Transactional
	public void delete(Long userId, Long userSubscriptionId) {
		userService.get(userId);
		userSubscriptionRepository
				.findById(userSubscriptionId)
				.orElseThrow(() -> new EntityNotFoundException(
						"User subscription %d not found".formatted(userSubscriptionId)));
		userSubscriptionRepository.deleteById(userSubscriptionId);
	}

}
