package org.quantum.usm.rest.controller.v1;

import org.quantum.usm.dto.CreateUserSubscriptionDto;
import org.quantum.usm.dto.UserDto;
import org.quantum.usm.entity.User;
import org.quantum.usm.entity.UserSubscription;
import org.quantum.usm.exception.EntityNotFoundException;
import org.quantum.usm.mapper.user.UserMapper;
import org.quantum.usm.mapper.usersubscription.UserSubscriptionMapper;
import org.quantum.usm.service.user.UserService;
import org.quantum.usm.service.usersubscription.UserSubscriptionService;
import org.quantum.usm.validation.OnCreate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	private final UserSubscriptionService userSubscriptionService;
	private final UserMapper userMapper;
	private final UserSubscriptionMapper userSubscriptionMapper;

	@PostMapping
	public ResponseEntity<?> createUser(@Validated(OnCreate.class) @RequestBody UserDto userDto) {
		User user = userService.create(userDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toDto(user));
	}

	@PostMapping("/{id}/subscriptions")
	public ResponseEntity<?> createUserSubscription(
			@PathVariable Long id,
			@Validated @RequestBody CreateUserSubscriptionDto userSubscriptionDto) {
		UserSubscription userSubscription = userSubscriptionService.create(id, userSubscriptionDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(userSubscriptionMapper.toReadDto(userSubscription));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		userService.delete(id);
		return ResponseEntity.ok(null);
	}

	@DeleteMapping("/{userId}/subscriptions/{subscriptionId}")
	public ResponseEntity<?> deleteUserSubscription(@PathVariable Long userId, @PathVariable Long subscriptionId) {
		User user = userService.get(userId);
		user.getSubscriptions().stream()
				.filter(userSubs -> userSubs.getId().equals(subscriptionId))
				.findAny()
				.orElseThrow(() -> {
					log.warn("user subscription id: {} not found", subscriptionId);
					return new EntityNotFoundException("User subscription %d not found".formatted(subscriptionId));
				});
		userSubscriptionService.delete(subscriptionId);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> get(@PathVariable Long id) {
		User user = userService.get(id);
		return ResponseEntity.status(HttpStatus.OK).body(userMapper.toDto(user));
	}

	@GetMapping("/{userId}/subscriptions")
	public ResponseEntity<?> getUserSubscriptions(@PathVariable Long userId) {
		User user = userService.get(userId);
		return ResponseEntity.status(HttpStatus.OK).body(userSubscriptionMapper.toReadDtos(user.getSubscriptions()));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(
			@PathVariable Long id,
			@Validated @RequestBody UserDto userDto) {
		User updatedUser = userService.update(id, userDto);
		return ResponseEntity.status(HttpStatus.OK).body(userMapper.toDto(updatedUser));
	}

}
