package org.quantum.usm.service.usersubscription;

import org.quantum.usm.entity.UserSubscription;

public interface UserSubscriptionService {

	Iterable<UserSubscription> getUserSubscriptions(Long userId);
	
	UserSubscription create(UserSubscription userSubscription);
	
	void delete(Long userSubscriptionId);
}
