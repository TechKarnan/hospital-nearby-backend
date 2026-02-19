CREATE TABLE products (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          price DECIMAL(10,2) NOT NULL,
                          quantity INT NOT NULL
);

CREATE TABLE cart_items (
                            id BIGSERIAL PRIMARY KEY,
                            product_id BIGINT REFERENCES products(id),
                            quantity INT NOT NULL
);

CREATE TABLE orders (
                        id BIGSERIAL PRIMARY KEY,
                        total_amount DECIMAL(10,2),
                        status VARCHAR(50),
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
