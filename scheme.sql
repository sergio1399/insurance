CREATE DATABASE insurance;

CREATE SEQUENCE IF NOT EXISTS contract_type_ids;
CREATE TABLE IF NOT EXISTS ContractType
(
  id INTEGER PRIMARY KEY DEFAULT NEXTVAL('contract_type_ids'),
  name VARCHAR(64) NOT NULL
);

CREATE TABLE IF NOT EXISTS Vehicle
(
  number VARCHAR(16) PRIMARY KEY,
  brand VARCHAR(64) NOT NULL,
  model VARCHAR(64) NOT NULL,
  year_of_creation INTEGER NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS contract_ids;
CREATE TABLE IF NOT EXISTS Contract
(
  id INTEGER PRIMARY KEY DEFAULT NEXTVAL('contract_ids'),
  contract_type_id INTEGER REFERENCES ContractType(id),
  serie VARCHAR(3) NOT NULL,
  number VARCHAR(8) NOT NULL,
  sign_date DATE NOT NULL,
  open_date DATE NOT NULL,
  expiration_date DATE,
  nds_sum REAL NOT NULL,
  sum_with_nds REAL NOT NULL,
  vehicle_number VARCHAR(16) REFERENCES Vehicle(number),
  note VARCHAR(255)
);


INSERT INTO ContractType (name) VALUES
    ('KASKO'),
    ('OSAGO'),
    ('VIP KASKO'),
    ('OSAGO POOR');

INSERT INTO Vehicle (number, brand, model, year_of_creation) VALUES
    ('A432CX58', 'Mitsubishi', 'Lancer X', 2012),
    ('O745HO58', 'Honda', 'Accord', 2017),
    ('A399PP58', 'Mazda', '6', 2018);