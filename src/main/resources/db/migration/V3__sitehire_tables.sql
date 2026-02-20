CREATE TABLE sitehire_users (
                                id BIGSERIAL PRIMARY KEY,
                                name VARCHAR(255),
                                phone VARCHAR(20) UNIQUE,
                                mpin VARCHAR(10),
                                role VARCHAR(20)
);

CREATE TABLE sitehire_worker_profiles (
                                          user_id BIGINT PRIMARY KEY,
                                          skill VARCHAR(50),
                                          experience_years INTEGER,
                                          latitude DOUBLE PRECISION,
                                          longitude DOUBLE PRECISION,
                                          available BOOLEAN
);