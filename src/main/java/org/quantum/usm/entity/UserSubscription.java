package org.quantum.usm.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Table(name = "user_subscriptions")
@Entity
@ToString(exclude = "user")
@EqualsAndHashCode(of = "id")
public class UserSubscription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "expiration_date")
	private LocalDate expirationDate;

	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	private Subscription subscription;

	public void setUser(User user) {
		this.user = user;
		this.user.getSubscriptions().add(this);
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
		this.subscription.getUserSubscriptions().add(this);
	}
}
