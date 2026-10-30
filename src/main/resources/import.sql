INSERT INTO employees (username, password, full_name, email, phone, position, role, active, created_at) VALUES 
('admin', '$2a$10$XUXEoDuGGZYKeMeV30xd6ON//ldi8oG1Gq4VOTMeZzadAR.XCdzWW', 'Administrator', 'admin@autodealer.com', '+1-555-000-0000', 'Admin', 'ROLE_ADMIN', true, CURRENT_TIMESTAMP),
('manager', '$2a$10$XUXEoDuGGZYKeMeV30xd6ON//ldi8oG1Gq4VOTMeZzadAR.XCdzWW', 'John Smith', 'john@autodealer.com', '+1-555-111-1111', 'Sales Manager', 'ROLE_EMPLOYEE', true, CURRENT_TIMESTAMP);

INSERT INTO suppliers (name, contact_person, email, phone, address, created_at) VALUES 
('AutoImport LLC', 'Peter Wilson', 'peter@autoimport.com', '+1-555-100-1000', 'New York, 5th Ave, 100', CURRENT_TIMESTAMP),
('MotorWorld Inc', 'Anna Davis', 'anna@motorworld.com', '+1-555-200-2000', 'Los Angeles, Main St, 200', CURRENT_TIMESTAMP);

INSERT INTO car_models (name, brand, color, upholstery, engine_type, engine_power, transmission, doors, description, created_at) VALUES 
('Camry 3.5', 'Toyota', 'White', 'Leather', 'Gasoline', 249, 'Automatic', 4, 'Business class sedan with excellent fuel economy', CURRENT_TIMESTAMP),
('RAV4 2.5', 'Toyota', 'Black', 'Fabric', 'Gasoline', 199, 'Automatic', 5, 'Popular crossover with spacious interior', CURRENT_TIMESTAMP),
('X5 3.0d', 'BMW', 'Blue', 'Leather', 'Diesel', 265, 'Automatic', 5, 'Premium crossover with advanced technology', CURRENT_TIMESTAMP),
('A6 2.0 TFSI', 'Audi', 'Gray', 'Leather', 'Gasoline', 252, 'Automatic', 4, 'Elegant sedan with premium features', CURRENT_TIMESTAMP),
('C-Class 200', 'Mercedes-Benz', 'Black', 'Leather', 'Gasoline', 204, 'Automatic', 4, 'Compact premium sedan', CURRENT_TIMESTAMP),
('Polo 1.6', 'Volkswagen', 'Red', 'Fabric', 'Gasoline', 110, 'Manual', 4, 'Reliable compact sedan', CURRENT_TIMESTAMP);

INSERT INTO car_model_suppliers (car_model_id, supplier_id) VALUES 
(1, 1), (2, 1), (3, 2), (4, 2), (5, 2), (6, 1);

INSERT INTO price_list (car_model_id, production_year, base_price, pre_sale_preparation, transport_costs, final_price, created_at) VALUES 
(1, 2024, 2800000.00, 50000.00, 30000.00, 2880000.00, CURRENT_TIMESTAMP),
(2, 2024, 2600000.00, 45000.00, 28000.00, 2673000.00, CURRENT_TIMESTAMP),
(3, 2024, 5200000.00, 80000.00, 50000.00, 5330000.00, CURRENT_TIMESTAMP),
(4, 2024, 3800000.00, 60000.00, 35000.00, 3895000.00, CURRENT_TIMESTAMP),
(5, 2024, 3500000.00, 55000.00, 32000.00, 3587000.00, CURRENT_TIMESTAMP),
(6, 2024, 1200000.00, 20000.00, 15000.00, 1235000.00, CURRENT_TIMESTAMP);

INSERT INTO customers (full_name, email, phone, address, active, created_at) VALUES 
('Robert Anderson', 'robert@email.com', '+1-555-400-4000', 'New York, Park Ave, 15', true, CURRENT_TIMESTAMP),
('Jennifer Miller', 'jennifer@email.com', '+1-555-500-5000', 'Los Angeles, Beach Rd, 20', true, CURRENT_TIMESTAMP);

INSERT INTO customer_requests (customer_name, email, phone, car_model_id, message, status, created_at, updated_at) VALUES 
('David Wilson', 'david@email.com', '+1-555-700-7000', 1, 'Interested in Toyota Camry in white. Is test drive available?', 'NEW', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Sarah Johnson', 'sarah@email.com', '+1-555-800-8000', 3, 'Want to know more about BMW X5. Is it available?', 'NEW', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
