CREATE TABLE users (
                       id UUID PRIMARY KEY,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(50) NOT NULL,
                       is_enabled BOOLEAN DEFAULT TRUE,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE refresh_tokens (
                                id UUID PRIMARY KEY,
                                user_id UUID REFERENCES users(id) ON DELETE CASCADE,
                                token VARCHAR(500) NOT NULL,
                                expiry_date TIMESTAMP NOT NULL,
                                revoked BOOLEAN DEFAULT FALSE
);