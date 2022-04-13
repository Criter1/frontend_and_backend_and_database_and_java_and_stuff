package ru.msu.cmc.webprak.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.msu.cmc.webprak.models.Film;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
public class FilmDAOTest {

    @Autowired
    private FilmDAO filmDAO;
    @Autowired
    private SessionFactory sessionFactory;

    @Test
    void testSimpleManipulations() {
        List<Film> filmListAll = (List<Film>) filmDAO.getAll();
        assertEquals(3, filmListAll.size());

        List<Film> avatarQuery = filmDAO.getFilmsByName("Avatar");
        assertEquals(1, avatarQuery.size());

        Film filmId3 = filmDAO.getById(3L);
        assertEquals(3, filmId3.getId());

        Film filmNotExist = filmDAO.getById(100L);
        assertNull(filmNotExist);
    }

    @Test
    void testUpdate() {
        Film updateFilm = filmDAO.getFilmByName("Avatar");
        updateFilm.setName("Avatar 2");
        updateFilm.setAuthor("smth");
        updateFilm.setCassete_price(0f);
        updateFilm.setDisk_price(0f);
        updateFilm.setCompany("dfg");
        filmDAO.update(updateFilm);

        Film avatar2 = filmDAO.getFilmByName("Avatar 2");
        assertEquals("Avatar 2", avatar2.getName());
        assertEquals("smth", avatar2.getAuthor());
        assertEquals(0f, avatar2.getCassete_price());
        assertEquals(0f, avatar2.getDisk_price());
        assertEquals("dfg", avatar2.getCompany());
    }

    @Test
    void testDelete() {
        Film avatar = filmDAO.getFilmByName("Avatar");
        filmDAO.delete(avatar);

        Film avatar2 = filmDAO.getFilmByName("Avatar");
        assertNull(avatar2);
    }

    @BeforeEach
    void beforeEach() {
        List<Film> filmList = new ArrayList<>();
        filmList.add(new Film(1L, "Avatar", "James Cameron", "20th Century Fox", 2009L, 0.0f, 8.0f));
        filmList.add(new Film(2L, "Pulp Fiction", "Quentin Tarantino", "A Band Apart", 1994L, 10.0f, 0.0f));
        filmList.add(new Film(3L, "Forrest Gump", "Robert Zemeckis", "The Tisch Company", 1994L, 9.0f, 0.0f));
        filmDAO.saveCollection(filmList);
    }

    @BeforeAll
    @AfterEach
    void annihilation() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("TRUNCATE film RESTART IDENTITY CASCADE;").executeUpdate();
            session.createSQLQuery("ALTER SEQUENCE film_id_seq RESTART WITH 1;").executeUpdate();
            session.getTransaction().commit();
        }
    }
}