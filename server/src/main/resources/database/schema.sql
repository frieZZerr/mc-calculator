CREATE TABLE IF NOT EXISTS products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    image_url VARCHAR(256),

    calories_per_100g INT,
    calories_portion INT,
    fat_per_100g INT,
    fat_portion INT,
    carbs_per_100g INT,
    carbs_portion INT,
    protein_per_100g INT,
    protein_portion INT,
    product_description TEXT,
    ingredients TEXT,
    allergens TEXT
);
