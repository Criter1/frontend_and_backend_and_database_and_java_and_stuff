package ru.msu.cmc.webprak.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.msu.cmc.webprak.models.Person;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(locations="classpath:application.properties")
public class PersonDAOTest {

    @Autowired
    private PersonDAO personDAO;
    @Autowired
    private SessionFactory sessionFactory;

    @Test
    void testSimpleManipulations() {
        List<Person> personListAll = (List<Person>) personDAO.getAll();
        assertEquals(3, personListAll.size());

        List<Person> avatarQuery = personDAO.getPersonsByName("Dee Snider");
        assertEquals(1, avatarQuery.size());

        Person personId3 = personDAO.getById(3L);
        assertEquals(3, personId3.getId());

        Person personNotExist = personDAO.getById(100L);
        assertNull(personNotExist);
    }

    @Test
    void testUpdate() {
        Person updatePerson = personDAO.getPersonByName("Dee Snider");
        updatePerson.setName("Dee Spider");
        updatePerson.setAddress("sdf");
        updatePerson.setPhone("2356");
        personDAO.update(updatePerson);

        Person avatar2 = personDAO.getPersonByName("Dee Spider");
        assertEquals("Dee Spider", avatar2.getName());
        assertEquals("sdf", avatar2.getAddress());
        assertEquals("2356", avatar2.getPhone());
    }

    @Test
    void testDelete() {
        Person avatar = personDAO.getPersonByName("Dee Snider");
        personDAO.delete(avatar);

        Person avatar2 = personDAO.getPersonByName("Dee Snider");
        assertNull(avatar2);
    }

    @BeforeEach
    void beforeEach() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(1L, "Dee Snider", "+19877658798", "Микрорайон Ленинские Горы, д1с52"));
        personList.add(new Person(2L, "Corey Taylor", "+17659877698", "Микрорайон Ленинские Горы, д1с48"));
        personList.add(new Person(3L, "Jimi Hendrix", "+17656554", "Микрорайон Ленинские Горы, д1с43"));
        personDAO.saveCollection(personList);
    }

    @BeforeAll
    @AfterEach
    void annihilation() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("TRUNCATE person RESTART IDENTITY CASCADE;").executeUpdate();
            session.createSQLQuery("ALTER SEQUENCE person_id_seq RESTART WITH 1;").executeUpdate();
            session.getTransaction().commit();
        }
    }
}