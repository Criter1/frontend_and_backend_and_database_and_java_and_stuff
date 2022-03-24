INSERT INTO film VALUES (1, 'Avatar', 'James Cameron', '20th Century Fox', '2009', 0.0, 8.0),
                        (2, 'Pulp Fiction', 'Quentin Tarantino', 'A Band Apart', '1994', 10.0, 0.0),
                        (3, 'Forrest Gump', 'Robert Zemeckis', 'The Tisch Company', '1994', 9.0, 0.0);

INSERT INTO person VALUES (1, 'Dee Snider', '+19877658798', 'Микрорайон Ленинские Горы, д1с52'),
                        (2, 'Corey Taylor', '+17659877698', 'Микрорайон Ленинские Горы, д1с48'),
                        (3, 'Jimi Hendrix', '+17656554', 'Микрорайон Ленинские Горы, д1с43');

INSERT INTO film_entity VALUES (1, 1, 2, TRUE),
                               (2, 2, 1, TRUE),
                               (3, 3, 1, TRUE);

INSERT INTO film_record VALUES (1, 1, 1, '12-01-2021', '12-02-2021', 8.0),
                               (2, 2, 2, '11-04-2021', NULL, 10.0),
                               (3, 3, 3, '12-01-2020', '12-02-2021', 9.0);
