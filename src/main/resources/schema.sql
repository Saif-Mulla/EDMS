CREATE TABLE IF NOT EXISTS employee (
  id INT AUTO_INCREMENT PRIMARY KEY,
  employee_id VARCHAR(10) NOT NULL UNIQUE,
  first_name VARCHAR(255),
  middle_name VARCHAR(255),
  last_name VARCHAR(255),
  full_name VARCHAR(512),
  designation VARCHAR(255),
  department VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS employee_address (
  id INT AUTO_INCREMENT PRIMARY KEY,
  employee_id VARCHAR(10) NOT NULL,
  address_type VARCHAR(255) NOT NULL,
  address1 VARCHAR(500),
  address2 VARCHAR(500),
  postal_code VARCHAR(255),
  CONSTRAINT fk_emp FOREIGN KEY (employee_id)
    REFERENCES employee(id) ON DELETE CASCADE
);