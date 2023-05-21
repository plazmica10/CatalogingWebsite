INSERT INTO GENRE (name) VALUES ( 'Fantasy' );
INSERT INTO GENRE (name) VALUES ( 'Adventure' );
INSERT INTO GENRE (name) VALUES ( 'Mystery' );
INSERT INTO GENRE (name) VALUES ( 'Horror' );
INSERT INTO GENRE (name) VALUES ( 'Romance' );
INSERT INTO GENRE (name) VALUES ( 'Science Fiction' );

INSERT INTO ACTIVATION_REQUEST (email, phone, message, date, status) VALUES ( 'example@gmail.com', '0631111111', 'Test', DATE '2022-9-11', 0 );

INSERT INTO USERS (username, name, surname, email, password, date, photo, description, role) VALUES ( 'test', 'John', 'Doe', 'john@gmail.com', '123', DATE '1969-4-20', 'photo', 'opis', 0 );
INSERT INTO USERS (username, name, surname, email, password, date, photo, description, role) VALUES ( 'daisyMeRollin', 'Joanne', 'Rowling', 'misBigJ@gmail.com', 'pass123', DATE '1965-7-31', 'photo', 'Tot G', 1 );
INSERT INTO AUTHORS (id, is_active) VALUES ( 2, 1 );

INSERT INTO REVIEW (rating, comment, date, user_id) VALUES ( 5, 'Iz good', DATE '2022-4-16', 1 );
INSERT INTO REVIEW (rating, comment, date, user_id) VALUES ( 10, 'Shit slaps', DATE '2023-7-7', 2 );

INSERT INTO BOOK (isbn, title, photo, date, pages, description, genre_id, rating) VALUES ( '0747532745', 'Harry Potter and the Philosopher''s Stone', 'photo', DATE '2000-3-25', 223, 'description', 1, 5 );
INSERT INTO BOOK (isbn, title, photo, date, pages, description, genre_id, rating) VALUES ( '0345339738', 'The Fellowship of the Ring', 'photo', DATE '1954-7-29', 423, 'description', 1, 5 );

INSERT INTO AUTHORS_BOOKS (author_id, books_id) VALUES ( 2, 1 );

INSERT INTO SHELF_ITEM (book_id) VALUES ( 1 );

INSERT INTO ITEM_REVIEWS(shelf_item_id, review_id) VALUES (1, 1);

INSERT INTO SHELF (name, is_primary) VALUES ( 'My Shelf', 1 );
INSERT INTO SHELF (name, is_primary) VALUES ( 'PrimarnaPolica', 0 );

INSERT INTO ITEM_ON_SHELF (shelf_id, shelfitem_id) VALUES ( 1, 1 );