package org.quantum.usm.service.usersubscription;

import java.time.LocalDate;
import java.util.Optional;

import org.quantum.usm.dto.CreateUserSubscriptionDto;
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
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	public UserSubscription create(Long userId, CreateUserSubscriptionDto userSubscriptionDto) {
		log.info("Create subscription: userId: {}, subscription: {}", userId, userSubscriptionDto);
		User user = userService.get(userId);
		Subscription subscription = subscriptionService.get(userSubscriptionDto.subscriptionId());
		Optional<UserSubscription> existedUserSubscription = userSubscriptionRepository
				.findByUserIdAndSubscriptionId(userId, userSubscriptionDto.subscriptionId());
		if (existedUserSubscription.isPresent()) {
			renewSubscriptionIfNeeded(existedUserSubscription.get(), userSubscriptionDto.expirationDate());
			return existedUserSubscription.get();
		}
		UserSubscription userSubscription = new UserSubscription();
		userSubscription.setUser(user);
		userSubscription.setSubscription(subscription);
		userSubscription.setExpirationDate(userSubscriptionDto.expirationDate());
		return userSubscriptionRepository.save(userSubscription);
	}

	@Override
	@Transactional
	public void delete(Long userSubscriptionId) {
		userSubscriptionRepository
				.findById(userSubscriptionId)
				.orElseThrow(() -> {
					log.warn("user subscription id: {} not found", userSubscriptionId);
					return new EntityNotFoundException(
							"User subscription id:%d not found".formatted(userSubscriptionId));
				});
		userSubscriptionRepository.deleteById(userSubscriptionId);
	}

	private void renewSubscriptionIfNeeded(UserSubscription subscription, LocalDate newDate) {
		if (subscription.getExpirationDate().isEqual(newDate)) {
			log.warn("user subscription id: {} already exists", subscription.getId());
			throw new EntityAlreadyExistsException(
					"User subscription id:%d already exists".formatted(subscription.getId()));
		}
		if (subscription.getExpirationDate().isBefore(newDate)) {
			subscription.setExpirationDate(newDate);
		}
	}

}
