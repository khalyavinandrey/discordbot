CREATE TABLE message (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    message VARCHAR(255) NOT NULL,
    created_at timestamptz DEFAULT current_timestamp
);