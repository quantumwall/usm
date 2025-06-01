package org.quantum.usm.service.usersubscription;

import org.quantum.usm.dto.CreateUserSubscriptionDto;
import org.quantum.usm.entity.UserSubscription;

public interface UserSubscriptionService {

	Iterable<UserSubscription> getUserSubscriptions(Long userId);

	UserSubscription create(Long userId, CreateUserSubscriptionDto userSubscription);

	void delete(Long userSubscriptionId);
}
