package org.quantum.usm.repository;

import org.quantum.usm.entity.Subscription;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {

	@Query(value = """
			SELECT * FROM subscriptions s
			WHERE s.id IN
			 		(
					SELECT subscription_id FROM
				 		(
				 			SELECT us.subscription_id, count(us.subscription_id)  FROM user_subscriptions us
							GROUP BY subscription_id
							ORDER BY count DESC
							LIMIT 3
						)
					);
			""", nativeQuery = true)
	Iterable<Subscription> findTopThreeSubscriptions();
}
