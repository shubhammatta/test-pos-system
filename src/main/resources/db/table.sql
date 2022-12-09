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

DROP TABLE IF EXISTS WORKER_NODE;
CREATE TABLE WORKER_NODE
(
    ID BIGINT NOT NULL AUTO_INCREMENT COMMENT 'auto increment id',
    HOST_NAME VARCHAR(64) NOT NULL COMMENT 'host name',
    PORT VARCHAR(64) NOT NULL COMMENT 'port',
    TYPE INT NOT NULL COMMENT 'node type: ACTUAL or CONTAINER',
    LAUNCH_DATE DATE NOT NULL COMMENT 'launch date',
    MODIFIED TIMESTAMP NOT NULL COMMENT 'modified time',
    CREATED TIMESTAMP NOT NULL COMMENT 'created time',
    PRIMARY KEY(ID)
)
    COMMENT='DB WorkerID Assigner for UID Generator',ENGINE = INNODB;