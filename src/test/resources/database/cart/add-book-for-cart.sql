INSERT INTO books (id, title, author, isbn, price, cover_image, description)
VALUES (3, 'new book', 'S.King', '212110590', 50.99, 'book.jpg', 'Horror book');

INSERT INTO book_category (book_id, category_id) VALUES (3, 2)