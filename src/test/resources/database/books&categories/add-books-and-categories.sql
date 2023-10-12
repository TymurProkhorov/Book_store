-- counter reset for books table
ALTER TABLE books AUTO_INCREMENT = 1;

-- counter reset for categories table
ALTER TABLE categories AUTO_INCREMENT = 1;

-- counter reset for book_category table
ALTER TABLE book_category AUTO_INCREMENT = 1;

INSERT INTO books (id, title, author, isbn, price, cover_image, description)
VALUES (1 ,'The mist', 'Stephen King', '1234567890', 100.00, 'mist.jpg', 'story about people trapped in shop with monsters outside');

INSERT INTO books (id, title, author, isbn, price, cover_image, description)
VALUES (2, 'Pet cemetery', 'S.King', '9876543210', 50.00, 'PetCemetery.jpg', 'story about resurrectional cemetery');

INSERT INTO categories (id, name, description) VALUES (1, 'Fiction', 'Fiction books');
INSERT INTO categories (id, name, description) VALUES (2, 'Horror', 'Horror books');

INSERT INTO book_category (book_id, category_id) VALUES (1, 1);
INSERT INTO book_category (book_id, category_id) VALUES (1, 2);
INSERT INTO book_category (book_id, category_id) VALUES (2, 2);