CREATE TABLE IF NOT EXISTS users
(
	id BIGSERIAL PRIMARY KEY,
	username VARCHAR(255) NOT NULL UNIQUE,
	firstname VARCHAR(255),
	lastname VARCHAR(255),
	birthday DATE
);

CREATE TABLE IF NOT EXISTS subscriptions
(
	id SERIAL PRIMARY KEY,
	title TEXT NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS user_subscriptions
(
	id BIGSERIAL PRIMARY KEY,
	expiration_date DATE NOT NULL,
	user_id BIGINT REFERENCES users ON DELETE CASCADE,
	subscription_id INT REFERENCES subscriptions ON DELETE CASCADE,
	UNIQUE (user_id, subscription_id)
);
