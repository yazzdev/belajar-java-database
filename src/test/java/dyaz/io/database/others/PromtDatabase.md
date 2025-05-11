## Promt Database

### Create Database
```sql
CREATE DATABASE belajar_java_database;
```

### Create Table customers
```sql
CREATE TABLE customers (
    id VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    CONSTRAINT email_unique UNIQUE (email),
    PRIMARY KEY (id)
) ENGINE = InnoDB;
```
### Show customers table
```sql
SELECT * FROM customers;
```
### Create Table admin
```sql
CREATE TABLE admin (
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    PRIMARY KEY (username)
) ENGINE = InnoDB;
```
### Add Value into admin table
```sql
INSERT INTO admin(username, password) VALUES ('admin', 'admin123');
```
### Show admin table
```sql
SELECT * FROM admin;
```
### Crate Table comments
```sql
CREATE TABLE comments (
    id INT NOT NULL AUTO_INCREMENT,
    email VARCHAR(100) NOT NULL,
    comment TEXT,
    PRIMARY KEY (id)
) ENGINE = InnoDB;
```

