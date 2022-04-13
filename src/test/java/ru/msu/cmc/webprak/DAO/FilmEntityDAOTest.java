package ru.msu.cmc.webprak.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.msu.cmc.webprak.models.FilmEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
public class FilmEntityDAOTest {

    @Autowired
    private FilmEntityDAO filmEntityDAO;
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private FilmDAO filmDAO;

    @Test
    void testSimpleManipulations() {
        List<FilmEntity> filmEntityListAll = (List<FilmEntity>) filmEntityDAO.getAll();
        assertEquals(3, filmEntityListAll.size());

        FilmEntity filmEntityId3 = filmEntityDAO.getById(3L);
        assertEquals(3, filmEntityId3.getId());

        FilmEntity filmEntityNotExist = filmEntityDAO.getById(100L);
        assertNull(filmEntityNotExist);
    }

    @Test
    void testUpdate() {
        FilmEntity updateFilmEntity = filmEntityDAO.getById(1L);
        updateFilmEntity.setIs_vacant(false);
        updateFilmEntity.setCarrier_type(FilmEntity.carrier.NO_CARRIER);
        updateFilmEntity.setFilm_id(filmDAO.getById(1L));
        filmEntityDAO.update(updateFilmEntity);

        FilmEntity avatar2 = filmEntityDAO.getById(1L);
        assertFalse(avatar2.getIs_vacant());
        assertEquals(FilmEntity.carrier.NO_CARRIER, avatar2.getCarrier_type());
        assertEquals(filmDAO.getById(2L), avatar2.getFilm_id());
    }

    @Test
    void testDelete() {
        FilmEntity disk = filmEntityDAO.getById(1L);
        filmEntityDAO.delete(disk);

        FilmEntity disk2 = filmEntityDAO.getById(1L);
        assertNull(disk2);
    }

    @BeforeEach
    void beforeEach() {
        List<FilmEntity> filmEntityList = new ArrayList<>();
        filmEntityList.add(new FilmEntity(1L, filmDAO.getById(1L), FilmEntity.carrier.DISK, true));
        filmEntityList.add(new FilmEntity(2L, filmDAO.getById(2L), FilmEntity.carrier.DISK, true));
        filmEntityList.add(new FilmEntity(3L, filmDAO.getById(3L), FilmEntity.carrier.CASSETE_AND_DISK, true));
        filmEntityDAO.saveCollection(filmEntityList);
    }

    @BeforeAll
    @AfterEach
    void annihilation() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("TRUNCATE film_entity RESTART IDENTITY CASCADE;").executeUpdate();
            session.createSQLQuery("ALTER SEQUENCE film_entity_id_seq RESTART WITH 1;").executeUpdate();
            session.getTransaction().commit();
        }
    }
}