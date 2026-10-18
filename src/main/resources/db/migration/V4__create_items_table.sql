CREATE TABLE IF NOT EXISTS items (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    type_id BIGINT NOT NULL,
    status_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,

    CONSTRAINT fk_items_type
        FOREIGN KEY (type_id)
        REFERENCES types (id),

    CONSTRAINT fk_items_status
        FOREIGN KEY (status_id)
        REFERENCES statuses (id),

    CONSTRAINT fk_items_user
        FOREIGN KEY (user_id)
        REFERENCES users (id)
);
