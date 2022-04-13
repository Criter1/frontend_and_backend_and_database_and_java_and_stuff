package ru.msu.cmc.webprak.DAO.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.webprak.DAO.PersonDAO;
import ru.msu.cmc.webprak.models.Person;
//import ru.msu.cmc.webprak.utils.HibernateUtil;

import java.util.List;

@Repository
public class PersonDAOImpl extends CommonDAOImpl<Person, Long> implements PersonDAO {

    public PersonDAOImpl() {
        super(Person.class);
    }

    @Override
    public List<Person> getPersonsByName(String personName) {
        Session session = sessionFactory.openSession();
        Query<Person> query = session.createQuery("FROM Person WHERE name LIKE :gotName", Person.class)
                .setParameter("gotName", "%" + personName + "%");
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList();
    }
    @Override
    public Person getPersonByName(String personName) {
        Session session = sessionFactory.openSession();
        Query<Person> query = session.createQuery("FROM Person WHERE name LIKE :gotName", Person.class)
                .setParameter("gotName", "%" + personName + "%");
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList().get(0);
    }
}