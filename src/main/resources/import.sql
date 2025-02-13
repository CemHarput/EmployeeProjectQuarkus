CREATE TABLE Employee
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    name      VARCHAR(255)   NOT NULL,
    surname   VARCHAR(255)   NOT NULL,
    age       INT            NOT NULL,
    salary    DECIMAL(38, 2) NOT NULL,
    workYears INT            NOT NULL,
    title     VARCHAR(255)   NOT NULL
);



INSERT INTO Employee (id, name, surname, age, salary, workYears, title)
VALUES (1, 'John', 'Doe', 30, 50000, 5, 'Developer');

INSERT INTO Employee (id, name, surname, age, salary, workYears, title)
VALUES (2, 'Jane', 'Smith', 28, 55000, 3, 'Tester');
