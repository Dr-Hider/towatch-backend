Alter TABLE users
    ADD CONSTRAINT uq_username UNIQUE (username);

ALTER TABLE types
    ADD CONSTRAINT uq_types_name UNIQUE (type_name);

ALTER TABLE statuses
    ADD CONSTRAINT uq_statuses_name UNIQUE (status_name);
