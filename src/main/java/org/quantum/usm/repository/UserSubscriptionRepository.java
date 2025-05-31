package org.quantum.usm.repository;

import org.quantum.usm.entity.UserSubscription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSubscriptionRepository extends CrudRepository<UserSubscription, Long> {

	Iterable<UserSubscription> findByUserId(Long userId);
}
