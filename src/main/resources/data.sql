DROP TABLE IF EXISTS players;
DROP TABLE IF EXISTS transactions;

CREATE TABLE players (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  balance DECIMAL(10,2) DEFAULT 0
);

INSERT INTO players (first_name, last_name, balance) VALUES
  ('Sam', 'Wilson', 300.00),
  ('Jhon', 'Snow', 300.00),
  ('Bill', 'Gates', 100000.00);

 CREATE TABLE transactions (
    id INT PRIMARY KEY,
    player_id INT NOT NULL,
    type VARCHAR(250) NOT NULL,
    amount DECIMAL(10,2) DEFAULT 0,
    creation_date TIMESTAMP NOT NULL
  );

 INSERT INTO transactions (id, player_id, type, amount, creation_date) VALUES
    (1001, 2, 'wager', 300.00, '2021-08-22 22:01:00.000'),
    (1002, 2, 'wager', 123.00, '2021-08-22 22:02:57.544'),
    (1003, 2, 'win', 43.00, '2021-08-22 22:25:03.544'),
    (1004, 2, 'win', 12.00, '2021-08-22 22:25:04.544'),
    (1005, 2, 'wager', 1231.00, '2021-08-22 22:05:57.544'),
    (1006, 2, 'win', 123.00, '2021-08-22 22:25:06.544'),
    (1007, 2, 'win', 423.00, '2021-08-22 22:25:07.544'),
    (1008, 2, 'win', 12.11, '2021-08-22 22:25:08.544'),
    (1009, 2, 'wager', 32.23, '2021-08-22 22:25:09.544'),
    (1010, 2, 'win', 15.41, '2021-08-22 22:25:10.544'),
    (1011, 2, 'win', 1231.51, '2021-08-22 22:25:11.544'),
    (1012, 2, 'win', 123.51, '2021-08-22 22:25:12.544'),
    (1013, 2, 'win', 1212331.00, '2021-08-22 22:13:57.544'),
    (1014, 2, 'win', 142.54, '2021-08-22 22:25:14.544'),
    (1015, 2, 'wager', 43.54, '2021-08-22 22:25:15.544'),
    (1016, 2, 'wager', 15.11, '2021-08-22 22:25:16.544')

