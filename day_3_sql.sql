
-- group by

SELECT d.name, COUNT(e.id)
FROM department d
JOIN employee e ON d.id = e.department_id
GROUP BY d.name;

-- right join

SELECT d.name, COUNT(ae.asset_id) AS total_assets
FROM asset_employee ae
RIGHT JOIN employee e ON ae.employee_id = e.id
RIGHT JOIN department d ON e.department_id = d.id
GROUP BY d.name;

-- nested query

SELECT *
FROM employee e 
JOIN department d ON e.department_id = d.id
WHERE d.id = 1 
AND e.id IN (
    SELECT employee_id
    FROM asset_employee 
    WHERE asset_id IN (
        SELECT id 
        FROM asset
        WHERE category IN ('laptop')
    )
);