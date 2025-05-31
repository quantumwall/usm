package org.quantum.usm.service.subscription;

import org.quantum.usm.entity.Subscription;

public interface SubscriptionService {

	Iterable<Subscription> getAvailableSubscriptions();
}
