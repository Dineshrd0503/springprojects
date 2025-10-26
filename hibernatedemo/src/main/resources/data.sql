-- =================================================================
-- Sample Data for the Hibernate Demo Project
-- =================================================================

-- -----------------------------------------------------
-- Table: author
-- -----------------------------------------------------
INSERT INTO author (id, name) VALUES (1, 'J.K. Rowling');
INSERT INTO author (id, name) VALUES (2, 'J.R.R. Tolkien');
INSERT INTO author (id, name) VALUES (3, 'George R.R. Martin');


-- -----------------------------------------------------
-- Table: book
-- -----------------------------------------------------
-- Books by J.K. Rowling (author_id = 1)
INSERT INTO book (id, title, author_id) VALUES (1, 'Harry Potter and the Sorcerer''s Stone', 1);
INSERT INTO book (id, title, author_id) VALUES (2, 'Harry Potter and the Chamber of Secrets', 1);

-- Books by J.R.R. Tolkien (author_id = 2)
INSERT INTO book (id, title, author_id) VALUES (3, 'The Hobbit', 2);
INSERT INTO book (id, title, author_id) VALUES (4, 'The Lord of the Rings', 2);

-- Books by George R.R. Martin (author_id = 3)
INSERT INTO book (id, title, author_id) VALUES (5, 'A Game of Thrones', 3);


-- -----------------------------------------------------
-- Table: user
-- Note: The `name` column is treated as a string as discussed.
-- The address columns (street, city, zip_code) are part of the user table because Address is @Embeddable.
-- -----------------------------------------------------
INSERT INTO user (id, name, street, city, zip_code) VALUES (1, 'Alice Smith', '123 Maple St', 'Springfield', '12345');
INSERT INTO user (id, name, street, city, zip_code) VALUES (2, 'Bob Johnson', '456 Oak Ave', 'Shelbyville', '67890');
INSERT INTO user (id, name, street, city, zip_code) VALUES (3, 'Charlie Brown', '789 Pine Ln', 'Capital City', '10112');


-- -----------------------------------------------------
-- Table: user_books (Join Table for User-Book relationship)
-- Represents which user has borrowed which book.
-- -----------------------------------------------------
-- Alice Smith (user_id = 1) has borrowed "The Hobbit" (book_id = 3)
INSERT INTO user_books (user_id, book_id) VALUES (1, 3);

-- Bob Johnson (user_id = 2) has borrowed "A Game of Thrones" (book_id = 5)
INSERT INTO user_books (user_id, book_id) VALUES (2, 5);

-- Alice Smith (user_id = 1) has also borrowed "Harry Potter and the Sorcerer's Stone" (book_id = 1)
INSERT INTO user_books (user_id, book_id) VALUES (1, 1);
