CREATE TABLE IF NOT EXISTS payments (
    uid BIGINT NOT NULL,
    final_price INT NOT NULL,
    customer_id VARCHAR(20) NOT NULL,
    requested_price INT NOT NULL,
    price_modifier DOUBLE DEFAULT 0,
    created_at DATETIME DEFAULT NULL,
    -- store additional items as metadata
    metadata JSON DEFAULT NULL,
    primary key ('uuid'),
    index idx_uid_created_at (uuid, created_at)
);