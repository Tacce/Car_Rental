DROP TABLE IF EXISTS PaymentMethods cascade;
DROP TABLE IF EXISTS Assistance cascade;
DROP TABLE IF EXISTS Cars cascade;
DROP TABLE IF EXISTS Mopeds cascade;
DROP TABLE IF EXISTS Vehicles cascade;
DROP TABLE IF EXISTS Rentals cascade;
DROP TABLE IF EXISTS Users cascade;

-- Creazione della tabella Utenti
CREATE TABLE Users (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50) NOT NULL,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
	age INT NOT NULL,
    license VARCHAR(20) UNIQUE NOT NULL
);

-- Creazione della tabella Veicoli
CREATE TABLE Vehicles (
    plate VARCHAR(20) PRIMARY KEY,
    model VARCHAR(50) NOT NULL,
    daily_price DECIMAL(10, 2) NOT NULL,
    available BOOLEAN NOT NULL
);

-- Creazione della tabella Motorini
CREATE TABLE Mopeds (
    plate VARCHAR(20) PRIMARY KEY REFERENCES Vehicles(plate),
    displacement INT NOT NULL
);

-- Creazione della tabella Assistenza
CREATE TABLE Assistance (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    phone VARCHAR(20) NOT NULL
);

-- Creazione della tabella Auto
CREATE TABLE Cars (
    plate VARCHAR(20) PRIMARY KEY REFERENCES Vehicles(plate),
    nseats INT NOT NULL,
    assistance_id INT REFERENCES Assistance(id)
);

-- Creazione della tabella Metodi di Pagamento
CREATE TABLE PaymentMethods (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

-- Creazione della tabella Noleggi
CREATE TABLE Rentals (
    id SERIAL PRIMARY KEY,
    user_username VARCHAR(50) REFERENCES Users(username),
    vehicle_plate VARCHAR(20) REFERENCES Vehicles(plate),
    ndays INT NOT NULL,
    payment_method_id INT REFERENCES PaymentMethods(id),
	vehicle_type INT NOT NULL,
    CONSTRAINT rentals_unique_user_vehicle UNIQUE (user_username, vehicle_plate)
);

create view cars_view as
select * from vehicles natural join cars;

create view mopeds_view as
select * from vehicles natural join mopeds;

-- Creazione del trigger INSTEAD OF INSERT per la vista cars_view
CREATE OR REPLACE FUNCTION insert_into_carview()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO vehicles (plate, model, daily_price, available)
    VALUES (NEW.plate, NEW.model, NEW.daily_price, NEW.available);

    INSERT INTO cars (plate, nseats, assistance_id)
    VALUES (NEW.plate, NEW.nseats, NEW.assistance_id);

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER carview_insert_trigger
INSTEAD OF INSERT ON cars_view
FOR EACH ROW
EXECUTE FUNCTION insert_into_carview();

-- Creazione del trigger INSTEAD OF INSERT per la vista mopeds_view
CREATE OR REPLACE FUNCTION insert_into_mopedsview()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO vehicles (plate, model, daily_price, available)
    VALUES (NEW.plate, NEW.model, NEW.daily_price, NEW.available);

    INSERT INTO mopeds (plate, displacement)
    VALUES (NEW.plate, NEW.displacement);

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER mopedsview_insert_trigger
INSTEAD OF INSERT ON mopeds_view
FOR EACH ROW
EXECUTE FUNCTION insert_into_mopedsview();


--Creazione Trigger per ELIMINAZIONE
CREATE OR REPLACE FUNCTION delete_car_function()
RETURNS TRIGGER AS $$
BEGIN
    DELETE FROM cars WHERE plate = OLD.plate;
    DELETE FROM vehicles WHERE plate = OLD.plate;
    RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER delete_car_trigger
INSTEAD OF DELETE ON cars_view
FOR EACH ROW
EXECUTE FUNCTION delete_car_function();

CREATE OR REPLACE FUNCTION delete_moped_function()
RETURNS TRIGGER AS $$
BEGIN
    DELETE FROM mopeds WHERE plate = OLD.plate;
    DELETE FROM vehicles WHERE plate = OLD.plate;
    RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER delete_moped_trigger
INSTEAD OF DELETE ON mopeds_view
FOR EACH ROW
EXECUTE FUNCTION delete_moped_function();

INSERT INTO assistance (id, name, phone) VALUES (0,'DEFAULT','123456');
INSERT INTO	paymentmethods (id, name) VALUES (0,'Cash'),(1,'Credit Card')


 
 