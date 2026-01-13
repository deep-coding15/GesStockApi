CREATE TABLE user_role (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    utilisateur_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    date_attribution TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (utilisateur_id) REFERENCES utilisateur(id),
    FOREIGN KEY (role_id) REFERENCES role(id)
);
