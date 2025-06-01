package org.quantum.usm.rest.controller.v1;

import org.quantum.usm.entity.Subscription;
import org.quantum.usm.mapper.subscription.SubscriptionMapper;
import org.quantum.usm.service.subscription.SubscriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

	private final SubscriptionService subscriptionService;
	private final SubscriptionMapper subscriptionMapper;

	@GetMapping
	public ResponseEntity<?> getAvailableSubscriptions() {
		Iterable<Subscription> subscriptions = subscriptionService.getAvailableSubscriptions();
		return ResponseEntity.status(HttpStatus.OK).body(subscriptionMapper.toDtos(subscriptions));
	}

	@GetMapping("/top")
	public ResponseEntity<?> getTopThreeSubscriptions() {
		Iterable<Subscription> subscriptions = subscriptionService.getTopThreeSubscriptions();
		return ResponseEntity.status(HttpStatus.OK).body(subscriptionMapper.toDtos(subscriptions));
	}

}
