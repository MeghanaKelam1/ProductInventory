INSERT INTO inventory (id, product_id, quantity) VALUES
(2001, 1001, 150),  -- Wireless Mouse
(2002, 1002, 80),   -- Mechanical Keyboard
(2003, 1003, 120),  -- USB-C Hub
(2004, 1004, 60),   -- Laptop Stand
(2005, 1005, 40);   -- Noise Cancelling Headphones



//for testing
2001	1001	150
2002	1002	80
2003	1003	120
2004	1004	60
2005	1005	40
2006	1006	10
2007	1007	80
2008	1008	20
2009	1009	2

INSERT INTO availability2 (availability_id, delivery_time, store) VALUES
(1001, 1, 'normal store'),
(1002, 0, 'normal store'),
(1003, 24, 'dark store'),
(1004, 48, 'dark store'),
(1005, 36, 'dark store');

ALTER TABLE availability2
ADD CONSTRAINT uq_availability_id UNIQUE (availability_id);

ALTER TABLE inventory2
ADD CONSTRAINT fk_inventory_availability
FOREIGN KEY (product_id)
REFERENCES availability2(availability_id);