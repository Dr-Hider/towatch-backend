ALTER TABLE types
ADD CONSTRAINT uq_types_name UNIQUE (type_name);

ALTER TABLE statuses
ADD CONSTRAINT uq_statuses_name UNIQUE (status_name);
