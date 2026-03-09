USE hex_training;

DROP TABLE IF EXISTS sales;

CREATE TABLE sales (
    id INT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(100),
    category VARCHAR(100),
    quantity INT,
    price DECIMAL(10,2),
    date_of_sale DATE
);

INSERT INTO sales (product_name, category, quantity, price, date_of_sale) VALUES
('Surveillance Drone','Defense',2,75000,'2026-03-01'),
('Radar System','Defense',5,20000,'2026-03-02'),
('Military Boots','Logistics',3,2500,'2026-03-03'),
('Tactical Uniform','Logistics',6,800,'2026-03-04'),
('Encrypted Radio','Communication',4,3000,'2026-03-05');

DROP PROCEDURE IF EXISTS get_sales_by_category;
DROP PROCEDURE IF EXISTS count_total_sales_by_category;

DELIMITER $$

CREATE PROCEDURE get_sales_by_category(IN p_category VARCHAR(100))
BEGIN
    SELECT *
    FROM sales
    WHERE category = p_category;
END $$

CREATE PROCEDURE count_total_sales_by_category(
    IN p_category VARCHAR(100),
    OUT total_sales INT
)
BEGIN
    SELECT COUNT(*)
    INTO total_sales
    FROM sales
    WHERE category = p_category;
END $$

DELIMITER ;

DROP VIEW IF EXISTS product_sales_summary;

CREATE VIEW product_sales_summary AS
SELECT 
    product_name,
    category,
    quantity,
    price
FROM sales;

CALL get_sales_by_category('Defense');

CALL count_total_sales_by_category('Defense', @total_sales);
SELECT @total_sales;

SELECT * FROM product_sales_summary;