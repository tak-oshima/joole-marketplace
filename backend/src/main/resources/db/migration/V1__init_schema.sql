-- V1__init_schema.sql

-- Create the user table
CREATE TABLE user (
    user_name VARCHAR(255) NOT NULL PRIMARY KEY,
    user_type VARCHAR(255) NOT NULL,
    user_password VARCHAR(255) NOT NULL
);

-- Create the project table
CREATE TABLE project (
    project_id INT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_name) REFERENCES user(user_name) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Create the product table
CREATE TABLE product (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_brand VARCHAR(255),
    certification VARCHAR(255)
);

-- Create the project_product table
CREATE TABLE project_product (
    project_product_id INT AUTO_INCREMENT PRIMARY KEY,
    project_id INT NOT NULL,
    product_id INT NOT NULL,
    FOREIGN KEY (project_id) REFERENCES project(project_id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Create the product_type table
CREATE TABLE product_type (
    product_type_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT NOT NULL,
    application VARCHAR(255),
    type VARCHAR(255),
    mounting_location VARCHAR(255),
    accessories VARCHAR(255),
    model_year DATE,
    FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Create the technical_detail table
CREATE TABLE technical_detail (
    technical_detail_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT NOT NULL,
    airflow INT,
    power INT,
    operating_voltage INT,
    fan_speed INT,
    FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Create the description table
CREATE TABLE description (
    description_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT NOT NULL,
    manufacturer VARCHAR(255),
    series VARCHAR(255),
    model VARCHAR(255),
    FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Create indexes on product table
CREATE INDEX idx_product_product_brand ON product(product_brand);

-- Create indexes on product_type table
CREATE INDEX idx_product_type_application ON product_type(application);
CREATE INDEX idx_product_type_type ON product_type(type);
CREATE INDEX idx_product_type_mounting_location ON product_type(mounting_location);
CREATE INDEX idx_product_type_accessories ON product_type(accessories);
CREATE INDEX idx_product_type_model_year ON product_type(model_year);

-- Create indexes on technical_detail table
CREATE INDEX idx_technical_detail_airflow ON technical_detail(airflow);
CREATE INDEX idx_technical_detail_power ON technical_detail(power);
CREATE INDEX idx_technical_detail_operating_voltage ON technical_detail(operating_voltage);
CREATE INDEX idx_technical_detail_fan_speed ON technical_detail(fan_speed);
