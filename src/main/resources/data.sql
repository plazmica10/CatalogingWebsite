INSERT INTO GENRE (name) VALUES ( 'Fantasy' );
INSERT INTO GENRE (name) VALUES ( 'Adventure' );
INSERT INTO GENRE (name) VALUES ( 'Mystery' );
INSERT INTO GENRE (name) VALUES ( 'Horror' );
INSERT INTO GENRE (name) VALUES ( 'Romance' );
INSERT INTO GENRE (name) VALUES ( 'Science Fiction' );

INSERT INTO ACTIVATION_REQUEST (email, phone, message, date, status) VALUES ( 'ognjen02110@gmail.com', '0631111111', 'Test', DATE '2022-9-11', 0 );

INSERT INTO USERS (username, name, surname, email, password, date, photo, description, role) VALUES ( 'test', 'John', 'Doe', 'john@gmail.com', '123', DATE '1969-4-20', 'photo', 'opis', 0 );
INSERT INTO USERS (username, name, surname, email, password, date, photo, description, role) VALUES ( 'user', 'John', 'Doe', 'johnny@gmail.com', '123', DATE '1969-4-20', 'photo', 'opis', 0 );
INSERT INTO USERS (username, name, surname, email, password, date, photo, description, role) VALUES ( 'nesto', 'John', 'Doe', 'mrDoe@gmail.com', '123', DATE '1969-4-20', 'photo', 'opis', 0 );
INSERT INTO USERS (username, name, surname, email, password, date, photo, description, role) VALUES ( 'username', 'John', 'Doe', 'doe.john@gmail.com', '123', DATE '1969-4-20', 'photo', 'opis', 0 );

INSERT INTO USERS (username, name, surname, email, password, date, photo, description, role) VALUES ( 'daisyMeRollin', 'Joanne', 'Rowling', 'misBigJ@gmail.com', 'pass123', DATE '1965-7-31', 'photo', 'Tot G', 1 );
INSERT INTO USERS (username, name, surname, email, password, date, photo, description, role) VALUES ( 'mrOrwell', 'George', 'Orwell', 'orwell@gmail.com', 'pass123', DATE '1965-7-31', 'photo', 'OldieButGoldie', 1 );
INSERT INTO USERS (username, name, surname, email, password, date, photo, description, role) VALUES ( 'tolkien', 'John', 'Ronald Reuel Tolkien', 'tolkien@gmail.com', 'pass123', DATE '1965-7-31', 'photo', 'LOTR fan', 1 );

INSERT INTO USERS (username, name, surname, email, password, date, photo, description, role) VALUES ( 'Shef', 'Gordon', 'Ramsay', 'idiotsandwich@gmail.com', 'pass123', DATE '1966-11-8', 'photo', 'flippin twat', 2 );

INSERT INTO AUTHORS (id, is_active) VALUES ( 5, 1 );
INSERT INTO AUTHORS (id, is_active) VALUES ( 6, 1 );
INSERT INTO AUTHORS (id, is_active) VALUES ( 7, 1 );

INSERT INTO REVIEW (rating, comment, date, user_id) VALUES ( 5, 'Iz good', DATE '2022-4-16', 1 );
INSERT INTO REVIEW (rating, comment, date, user_id) VALUES ( 10, 'Shit slaps', DATE '2023-7-7', 2 );

INSERT INTO BOOK (isbn, title, photo, date, pages, description, genre_id, rating) VALUES ( '0747532745', 'Harry Potter and the Philosopher''s Stone', 'photo', DATE '2000-3-25', 223, 'description', 1, 5 );
INSERT INTO BOOK (isbn, title, photo, date, pages, description, genre_id, rating) VALUES ( '0345339738', 'The Fellowship of the Ring', 'photo', DATE '1954-7-29', 423, 'description', 1, 5 );
INSERT INTO BOOK (isbn, title, photo, date, pages, description, genre_id, rating) VALUES ( '9789198577815', 'Nineteen Eighty-Four', 'photo', DATE '1949-7-29', 276, 'description', 6, 4 );
INSERT INTO BOOK (isbn, title, photo, date, pages, description, genre_id, rating) VALUES ( '9780451526342', 'Animal Farm', 'photo', DATE '1945-7-29', 276, 'description', 6, 4 );

INSERT INTO AUTHORS_BOOKS (author_id, books_id) VALUES ( 5, 1 );
INSERT INTO AUTHORS_BOOKS (author_id, books_id) VALUES ( 7, 2 );
INSERT INTO AUTHORS_BOOKS (author_id, books_id) VALUES ( 6, 3 );
INSERT INTO AUTHORS_BOOKS (author_id, books_id) VALUES ( 6, 4 );

INSERT INTO SHELF_ITEM (book_id) VALUES ( 1 );
INSERT INTO SHELF_ITEM (book_id) VALUES ( 2 );
INSERT INTO SHELF_ITEM (book_id) VALUES ( 3 );
INSERT INTO SHELF_ITEM (book_id) VALUES ( 4 );

//INSERT INTO ITEM_REVIEWS(shelf_item_id, review_id) VALUES (1, 1);

// users shelves
INSERT INTO SHELF (name, is_primary) VALUES ( 'Want to read', 1 );
INSERT INTO SHELF (name, is_primary) VALUES ( 'Currently reading', 1 );
INSERT INTO SHELF (name, is_primary) VALUES ( 'Read', 1 );
INSERT INTO SHELF (name, is_primary) VALUES ( 'Want to read', 1 );
INSERT INTO SHELF (name, is_primary) VALUES ( 'Currently reading', 1 );
INSERT INTO SHELF (name, is_primary) VALUES ( 'Read', 1 );
INSERT INTO SHELF (name, is_primary) VALUES ( 'Want to read', 1 );
INSERT INTO SHELF (name, is_primary) VALUES ( 'Currently reading', 1 );
INSERT INTO SHELF (name, is_primary) VALUES ( 'Read', 1 );
INSERT INTO SHELF (name, is_primary) VALUES ( 'Want to read', 1 );
INSERT INTO SHELF (name, is_primary) VALUES ( 'Currently reading', 1 );
INSERT INTO SHELF (name, is_primary) VALUES ( 'Read', 1 );
//author shelves
INSERT INTO SHELF (name, is_primary) VALUES ( 'Want to read', 1 );
INSERT INTO SHELF (name, is_primary) VALUES ( 'Currently reading', 1 );
INSERT INTO SHELF (name, is_primary) VALUES ( 'Read', 1 );
INSERT INTO SHELF (name, is_primary) VALUES ( 'Want to read', 1 );
INSERT INTO SHELF (name, is_primary) VALUES ( 'Currently reading', 1 );
INSERT INTO SHELF (name, is_primary) VALUES ( 'Read', 1 );
INSERT INTO SHELF (name, is_primary) VALUES ( 'Want to read', 1 );
INSERT INTO SHELF (name, is_primary) VALUES ( 'Currently reading', 1 );
INSERT INTO SHELF (name, is_primary) VALUES ( 'Read', 1 );

// users shelves connection
INSERT INTO USERS_SHELVES (user_id, shelves_id) VALUES ( 1, 1 );
INSERT INTO USERS_SHELVES (user_id, shelves_id) VALUES ( 1, 2 );
INSERT INTO USERS_SHELVES (user_id, shelves_id) VALUES ( 1, 3 );
INSERT INTO USERS_SHELVES (user_id, shelves_id) VALUES ( 2, 4 );
INSERT INTO USERS_SHELVES (user_id, shelves_id) VALUES ( 2, 5 );
INSERT INTO USERS_SHELVES (user_id, shelves_id) VALUES ( 2, 6 );
INSERT INTO USERS_SHELVES (user_id, shelves_id) VALUES ( 3, 7 );
INSERT INTO USERS_SHELVES (user_id, shelves_id) VALUES ( 3, 8 );
INSERT INTO USERS_SHELVES (user_id, shelves_id) VALUES ( 3, 9 );
INSERT INTO USERS_SHELVES (user_id, shelves_id) VALUES ( 4, 10 );
INSERT INTO USERS_SHELVES (user_id, shelves_id) VALUES ( 4, 11 );
INSERT INTO USERS_SHELVES (user_id, shelves_id) VALUES ( 4, 12 );
//authors shelves connection
INSERT INTO USERS_SHELVES (user_id, shelves_id) VALUES ( 5, 13 );
INSERT INTO USERS_SHELVES (user_id, shelves_id) VALUES ( 5, 14 );
INSERT INTO USERS_SHELVES (user_id, shelves_id) VALUES ( 5, 15 );
INSERT INTO USERS_SHELVES (user_id, shelves_id) VALUES ( 6, 16 );
INSERT INTO USERS_SHELVES (user_id, shelves_id) VALUES ( 6, 17 );
INSERT INTO USERS_SHELVES (user_id, shelves_id) VALUES ( 6, 18 );
INSERT INTO USERS_SHELVES (user_id, shelves_id) VALUES ( 7, 19 );
-- INSERT INTO USERS_SHELVES (user_id, shelves_id) VALUES ( 7, 20 );
-- INSERT INTO USERS_SHELVES (user_id, shelves_id) VALUES ( 7, 21 );

INSERT INTO ITEM_ON_SHELF (shelf_id, shelfitem_id) VALUES ( 1, 1 );