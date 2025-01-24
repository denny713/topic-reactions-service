CREATE TABLE vote
(
    vote_id      SERIAL PRIMARY KEY,
    topic_id     INT4,
    like_vote    BOOLEAN     NOT NULL,
    dislike_vote BOOLEAN     NOT NULL,
    created_by   VARCHAR(35) NOT NULL,
    created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);