-- Insert initial data into the newly created product table
INSERT INTO product (name, type, quantity, location, status) VALUES
('Router Model X', 'Router', 10, 'Data Center 1', 'Active'),
('Fiber Optic Cable 100m', 'Cable', 200, 'Warehouse A', 'Active'),
('4G LTE Modem', 'CPE', 50, 'Site 101', 'EOL'),
('Antenna Model Y', 'Antenna', 30, 'Site 102', 'Active'),
('Power Supply Unit 24V', 'Component', 100, 'Warehouse B', 'Active'),
('Switch Model Z', 'Switch', 25, 'Data Center 1', 'Active'),
('Ethernet Cable 50m', 'Cable', 500, 'Warehouse A', 'Active'),
('5G Base Station', 'RAN Equipment', 10, 'Site 101', 'In Maintenance'),
('Antenna Array 8x8', 'Antenna', 15, 'Site 102', 'Active'),
('Power Supply Unit 48V', 'Component', 75, 'Warehouse C', 'Active'),
('Server Rack 42U', 'Infrastructure', 20, 'Data Center 2', 'Active'),
('SFP Transceiver 10G', 'Transceiver', 150, 'Warehouse B', 'Active'),
('Patch Panel 24-port', 'Infrastructure', 40, 'Data Center 1', 'Active'),
('VoIP Phone Model T', 'CPE', 100, 'Office Storage', 'EOL'),
('UPS Backup System', 'Power', 5, 'Data Center 1', 'In Maintenance');