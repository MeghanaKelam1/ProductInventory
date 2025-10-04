INSERT INTO product (id, name, description, price) VALUES
(1001, 'Wireless Mouse', 'Ergonomic wireless mouse with adjustable DPI', 799.00),
(1002, 'Mechanical Keyboard', 'RGB backlit mechanical keyboard with blue switches', 2499.00),
(1003, 'USB-C Hub', '6-in-1 USB-C hub with HDMI, USB 3.0, and SD slots', 1599.00),
(1004, 'laptop', 'Aluminum adjustable laptop stand for desk setup', 1299.00),
(1005, 'Noise Cancelling Headphones', 'Over-ear headphones with ANC and Bluetooth', 4999.00);

-- Normal store: 30 minutes delivery
INSERT INTO availability1 (availability_id, delivery_time, store)
VALUES (1001, 1, 'normal store');

-- Normal store: instant delivery
INSERT INTO availability1 (availability_id, delivery_time, store)
VALUES (1002, 0, 'normal store');

-- Dark store: 24 hours delivery
INSERT INTO availability1 (availability_id, delivery_time, store)
VALUES (1003, 24, 'dark store');

-- Dark store: 48 hours delivery
INSERT INTO availability1 (availability_id, delivery_time, store)
VALUES (1004, 48, 'dark store');

-- Dark store: 36 hours delivery
INSERT INTO availability1 (availability_id, delivery_time, store)
VALUES (1005, 36, 'dark store');