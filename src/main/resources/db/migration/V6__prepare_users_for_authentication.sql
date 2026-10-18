ALTER TABLE users
    ALTER COLUMN password_hash TYPE VARCHAR(100);

CREATE UNIQUE INDEX uq_users_username_lower
    ON users (LOWER(username));
