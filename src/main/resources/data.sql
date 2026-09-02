INSERT INTO years (id_year, year_value) VALUES (default, 1994);
INSERT INTO years (id_year, year_value) VALUES (default, 1999);
INSERT INTO years (id_year, year_value) VALUES (default, 2014);

INSERT INTO genres (id_genre, name) VALUES (default, 'Accion');
INSERT INTO genres (id_genre, name) VALUES (default, 'Drama');
INSERT INTO genres (id_genre, name) VALUES (default, 'Ciencia ficcion');

INSERT INTO actors (id_actor, name) VALUES (default, 'Keanu Reeves');
INSERT INTO actors (id_actor, name) VALUES (default, 'Morgan Freeman');
INSERT INTO actors (id_actor, name) VALUES (default, 'Matthew McConaughey');

INSERT INTO movies (id_movie, title, synopsis, duration, year_id) VALUES (default, 'Matrix', 'Un hacker descubre que la realidad es una simulacion.', 136, 2);
INSERT INTO movies (id_movie, title, synopsis, duration, year_id) VALUES (default, 'Cadena perpetua', 'Dos presos forjan una amistad a lo largo de los anos.', 142, 1);
INSERT INTO movies (id_movie, title, synopsis, duration, year_id) VALUES (default, 'Interstellar', 'Un grupo de astronautas busca un nuevo hogar para la humanidad.', 169, 3);

INSERT INTO movies_genres (movie_id, genre_id) VALUES (1, 1);
INSERT INTO movies_genres (movie_id, genre_id) VALUES (1, 3);
INSERT INTO movies_genres (movie_id, genre_id) VALUES (2, 2);
INSERT INTO movies_genres (movie_id, genre_id) VALUES (3, 2);
INSERT INTO movies_genres (movie_id, genre_id) VALUES (3, 3);

INSERT INTO movies_actors (movie_id, actor_id) VALUES (1, 1);
INSERT INTO movies_actors (movie_id, actor_id) VALUES (2, 2);
INSERT INTO movies_actors (movie_id, actor_id) VALUES (3, 3);
