
CREATE TABLE books (
    book_id INT AUTO_INCREMENT PRIMARY KEY,  -- Unique ID for each book
    title VARCHAR(255) NOT NULL,             -- Book title
    author VARCHAR(255) NOT NULL,            -- Author's name
    genre VARCHAR(100),                      -- Genre of the book
    isbn VARCHAR(20) UNIQUE NOT NULL,        -- ISBN number
    total_copies INT DEFAULT 1,              -- Total copies in the library
    available_copies INT DEFAULT 1           -- Copies currently available
);

CREATE TABLE users (
    users_id INT AUTO_INCREMENT PRIMARY KEY,  -- Unique user ID
    external_id VARCHAR(255) UNIQUE,
    username VARCHAR(255) NOT NULL,              -- User's name
    email VARCHAR(255) UNIQUE NOT NULL,      -- Email (unique for each user)
    phone VARCHAR(15),                       -- Optional phone number
    join_date DATE DEFAULT (current_date())        -- Date the user joined
);

CREATE TABLE borrow_records (
    FOREIGN KEY (users_id) REFERENCES users(users_id),
    FOREIGN KEY (book_id) REFERENCES books(book_id),
    record_id INT AUTO_INCREMENT PRIMARY KEY,  -- Unique record ID
    users_id INT NOT NULL,                      -- User who borrowed the book
    book_id INT NOT NULL,                      -- The borrowed book
    borrow_date DATE DEFAULT (current_date()), -- Date of borrowing
    due_date DATE,                             -- Due date for returning the book
    return_date DATE,                          -- Date the book was returned
    fine DECIMAL(10, 2) DEFAULT 0.00          -- Fine for overdue books
);

CREATE TABLE admin (
    admin_id INT AUTO_INCREMENT PRIMARY KEY,   -- Unique ID for each credential
    email VARCHAR(255) UNIQUE NOT NULL,            -- User's email address (must be unique)
    passw VARCHAR(255) NOT NULL           -- Hashed password
);

ALTER TABLE books AUTO_INCREMENT =  1;
ALTER TABLE borrow_records AUTO_INCREMENT =  1;

UPDATE books
SET available_copies = available_copies - 1
WHERE book_id = 1;

INSERT INTO books (title, author, genre, isbn, total_copies, available_copies) VALUES
( 'The Catcher in the Rye', 'J.D. Salinger', 'Fiction', '9780316769488', 5, 5),
( 'Brave New World', 'Aldous Huxley', 'Dystopian', '9780060850524', 4, 4),
( 'The Road', 'Cormac McCarthy', 'Post-Apocalyptic', '9780307387899', 6,6 ),
( 'War and Peace', 'Leo Tolstoy', 'Historical', '9780199232765', 3, 3),
( 'Crime and Punishment', 'Fyodor Dostoevsky', 'Crime', '9780140449136', 4,4 ),
( 'Harry Potter and the Sorcerer’s Stone', 'J.K. Rowling', 'Fantasy', '9780590353427', 10, 10),
( 'The Alchemist', 'Paulo Coelho', 'Inspirational', '9780061122415', 7, 7),
('The Great Expectations', 'Charles Dickens', 'Fiction', '9780141439563', 8, 8),
( 'Dracula', 'Bram Stoker', 'Horror', '9780141439846', 5, 5),
( 'The Lord of the Rings', 'J.R.R. Tolkien', 'Fantasy', '9780544003415', 6, 6),
( 'The Hobbit', 'J.R.R. Tolkien', 'Fantasy', '9780547928227', 10, 10),
( 'Moby Dick', 'Herman Melville', 'Adventure', '9781503280786', 4,4),
( 'Frankenstein', 'Mary Shelley', 'Horror', '9780486282114', 5,5),
( 'Pride and Prejudice', 'Jane Austen', 'Romance', '9781503290563', 6,6),
( 'The Shining', 'Stephen King', 'Horror', '9780307743657', 3,3),
( 'The Catch-22', 'Joseph Heller', 'Satire', '9781451626650', 5,5),
( 'To Kill a Mockingbird', 'Harper Lee', 'Fiction', '9780060935467', 4,4),
( 'A Game of Thrones', 'George R.R. Martin', 'Fantasy', '9780553103540', 9,9),
( 'The Fault in Our Stars', 'John Green', 'Romance', '9780525478812', 8,8),
( 'Dune', 'Frank Herbert', 'Science Fiction', '9780441172719', 7,7);

INSERT INTO users (external_id, username, email, phone) VALUES
('ititiu19036', 'Alice Johnson', 'alice.johnson@example.com', '1234567890'),
('ititiu19039', 'Bob Smith', 'bob.smith@example.com', '9876543210'),
('ititiu19126', 'Charlie Brown', 'charlie.brown@example.com', '1231231234'),
('itdsiu21053', 'Diana Prince', 'diana.prince@example.com', '4564564567'),
('itdsiu21145', 'Eve Torres', 'eve.torres@example.com', '7897897890'),
('itdsiu20058', 'Frank Castle', 'frank.castle@example.com', '1011121314'),
('itdsiu18053', 'Grace Lee', 'grace.lee@example.com', '1413121110'),
('itcsiu17071', 'Hank Pym', 'hank.pym@example.com', '1617181920'),
('itcsiu21242', 'Ivy Taylor', 'ivy.taylor@example.com', '2122232425'),
('itcsiu20359', 'Jack Black', 'jack.black@example.com', '2524232221'),
('itcsiu19023', 'Karen White', 'karen.white@example.com', '2829303132'),
('itcsiu18094', 'Leo Green', 'leo.green@example.com', '3231302928'),
('itcsiu19053', 'Mona Lisa', 'mona.lisa@example.com', '3536373839'),
('itcsiu20071', 'Nick Fury', 'nick.fury@example.com', '3938373635'),
('itcsiu22222', 'Olivia Wilde', 'olivia.wilde@example.com', '4041424344');

INSERT INTO borrow_records (users_id, book_id, due_date)
VALUES (4, 8, '2025-11-15');

INSERT INTO borrow_records (users_id, book_id, due_date)
VALUES (3, 13, '2025-09-15');

INSERT INTO borrow_records (users_id, book_id, due_date)
VALUES (7, 15, '2025-11-2');

INSERT INTO borrow_records (users_id, book_id, due_date)
VALUES (6, 17, '2025-3-15');

UPDATE borrow_records
SET return_date = '2024-12-12'
WHERE record_id = 1;

UPDATE borrow_records
SET return_date = '2026-11-20'
WHERE record_id = 1;

SELECT * FROM books;
SELECT * FROM borrow_records;

DROP TRIGGER IF EXISTS before_return_update;
DROP procedure if EXISTS update_fine;

SET FOREIGN_KEY_CHECKS = 0;
SET FOREIGN_KEY_CHECKS = 1;
TRUNCATE TABLE books;
TRUNCATE TABLE borrow_records;

DELIMITER $$
CREATE PROCEDURE update_fine(record_id INT, return_date DATE, due_date DATE)
BEGIN
    IF return_date > due_date THEN
        UPDATE borrow_records
        SET fine = 50.00
        WHERE record_id = record_id;
    ELSE
        UPDATE borrow_records
        SET fine = 0.00
        WHERE record_id = record_id;
    END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER before_return_update
BEFORE UPDATE ON borrow_records
FOR EACH ROW
BEGIN
    IF NEW.return_date IS NOT NULL AND OLD.return_date IS NULL THEN
        UPDATE books
        SET available_copies = available_copies + 1
        WHERE book_id = NEW.book_id;
    END IF;
    IF NEW.return_date IS NOT NULL AND NEW.return_date > NEW.due_date THEN
        SET NEW.fine = 50.00;
    ELSE
        SET NEW.fine = 0.00;
    END IF;
END$$

DELIMITER ;

DELIMITER $$

CREATE TRIGGER after_borrow_insert
AFTER INSERT ON borrow_records
FOR EACH ROW
BEGIN
    IF (SELECT available_copies FROM books WHERE book_id = NEW.book_id) > 0 THEN
        UPDATE books
        SET available_copies = available_copies - 1
        WHERE book_id = NEW.book_id;
    ELSE
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Book is out of stock and cannot be borrowed.';
    END IF;
END$$

DELIMITER ;



