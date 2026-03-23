CREATE TABLE IF NOT EXISTS products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    image_url VARCHAR(256),
    calories_per_100g INT NOT NULL,
    calories_portion INT NOT NULL,
    fat_per_100g INT NOT NULL,
    fat_portion INT NOT NULL,
    carbs_per_100g INT NOT NULL,
    carbs_portion INT NOT NULL,
    protein_per_100g INT NOT NULL,
    protein_portion INT NOT NULL
);