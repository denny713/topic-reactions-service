CREATE TABLE account
(
    account_id   SERIAL PRIMARY KEY,
    email        VARCHAR(35) NOT NULL,
    full_name    VARCHAR(50) NOT NULL,
    password     VARCHAR(64) NOT NULL,
    created_by   VARCHAR(35) NOT NULL,
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);