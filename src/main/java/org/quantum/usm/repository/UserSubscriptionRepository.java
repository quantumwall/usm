package org.quantum.usm.repository;

import java.util.Optional;

import org.quantum.usm.entity.UserSubscription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSubscriptionRepository extends CrudRepository<UserSubscription, Long> {

	Iterable<UserSubscription> findByUserId(Long userId);

	Optional<UserSubscription> findByUserIdAndSubscriptionId(Long userId, Integer subscriptionId);
}
