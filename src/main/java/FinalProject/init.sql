CREATE DATABASE IF NOT EXISTS petadoption;
USE petadoption;

CREATE TABLE IF NOT EXISTS pets (
    id          BIGINT          NOT NULL AUTO_INCREMENT,
    name        VARCHAR(100)    NOT NULL,
    type        ENUM('DOG', 'CAT', 'BIRD', 'RABBIT', 'OTHER') NOT NULL,
    sex         ENUM('MALE', 'FEMALE')                         NOT NULL,
    age         INT             NOT NULL,
    weight      DECIMAL(5, 2)  NOT NULL,
    breed       VARCHAR(100)    NOT NULL,
    address     VARCHAR(255)    NOT NULL,
    status      ENUM('AVAILABLE', 'ADOPTED', 'PENDING')        NOT NULL DEFAULT 'AVAILABLE',
    created_at  DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (id)
);