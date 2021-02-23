CREATE TABLE coin
(
    id                          BIGSERIAL                       PRIMARY KEY,
    type                        VARCHAR(10)                     NOT NULL,
    name                        VARCHAR(50)                     NOT NULL,
    max_value                   DOUBLE PRECISION                NOT NULL,
    min_value                   DOUBLE PRECISION                NOT NULL,
    last_update_time            TIME                            NOT NULL,
    saved_date                  DATE                            NOT NULL
);