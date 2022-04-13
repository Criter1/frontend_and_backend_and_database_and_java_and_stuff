package ru.msu.cmc.webprak.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.msu.cmc.webprak.models.FilmRecord;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
public class FilmRecordDAOTest {

    @Autowired
    private FilmRecordDAO filmRecordDAO;
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private PersonDAO personDAO;
    @Autowired
    private FilmEntityDAO filmEntityDAO;

    @Test
    void testSimpleManipulations() {
        List<FilmRecord> filmRecordListAll = (List<FilmRecord>) filmRecordDAO.getAll();
        assertEquals(3, filmRecordListAll.size());

        FilmRecord filmRecordId3 = filmRecordDAO.getById(3L);
        assertEquals(3, filmRecordId3.getId());

        FilmRecord filmRecordNotExist = filmRecordDAO.getById(100L);
        assertNull(filmRecordNotExist);
    }

    @Test
    void testUpdate() {
        FilmRecord updateFilmRecord = filmRecordDAO.getById(1L);
        updateFilmRecord.setPrice(50f);
        updateFilmRecord.setDate_recieved(new Date(1638303000L));
        updateFilmRecord.setId_person(personDAO.getById(1L));
        updateFilmRecord.setId_film_entity(filmEntityDAO.getById(1L));
        updateFilmRecord.setDate_given(new Date(1608306000L));
        filmRecordDAO.update(updateFilmRecord);

        FilmRecord pricier = filmRecordDAO.getById(1L);
        assertEquals(50f, pricier.getPrice());
        assertEquals(new Date(1638303000L), pricier.getDate_recieved());
        assertEquals(personDAO.getById(1L), pricier.getId_person());
        assertEquals(filmEntityDAO.getById(1L), pricier.getId_film_entity());
        assertEquals(new Date(1608306000L), pricier.getDate_given());
    }

    @Test
    void testDelete() {
        FilmRecord avatar = filmRecordDAO.getById(1L);
        filmRecordDAO.delete(avatar);

        FilmRecord avatar2 = filmRecordDAO.getById(1L);
        assertNull(avatar2);
    }

    @BeforeEach
    void beforeEach() {
        List<FilmRecord> filmRecordList = new ArrayList<>();
        filmRecordList.add(new FilmRecord(1L, personDAO.getById(1L), filmEntityDAO.getById(1L), new Date(1638306000L), new Date(1638392400L), 8.0f));
        filmRecordList.add(new FilmRecord(2L, personDAO.getById(2L), filmEntityDAO.getById(2L), new Date(1635973200L), null, 10.0f));
        filmRecordList.add(new FilmRecord(3L, personDAO.getById(3L), filmEntityDAO.getById(3L), new Date(1606770000L), new Date(1638392400L), 9.0f));
        filmRecordDAO.saveCollection(filmRecordList);
    }

    @BeforeAll
    @AfterEach
    void annihilation() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("TRUNCATE film_record RESTART IDENTITY CASCADE;").executeUpdate();
            session.createSQLQuery("ALTER SEQUENCE film_record_id_seq RESTART WITH 1;").executeUpdate();
            session.getTransaction().commit();
        }
    }
}