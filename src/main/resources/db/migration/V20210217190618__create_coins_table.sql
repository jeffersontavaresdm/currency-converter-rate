CREATE TABLE coin
(
    id                          BIGSERIAL           PRIMARY KEY,
    type                        VARCHAR(10)         NOT NULL,
    name                        VARCHAR(50)         NOT NULL,
    value                       DOUBLE PRECISION    NOT NULL,
    last_update_date            TIMESTAMP           NOT NULL
);