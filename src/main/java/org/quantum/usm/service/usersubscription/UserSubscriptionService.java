package org.quantum.usm.service.usersubscription;

import org.quantum.usm.dto.UserSubscriptionDto;
import org.quantum.usm.entity.UserSubscription;

public interface UserSubscriptionService {

	Iterable<UserSubscription> getUserSubscriptions(Long userId);

	UserSubscription create(Long userId, UserSubscriptionDto userSubscription);

	void delete(Long userId, Long userSubscriptionId);
}
