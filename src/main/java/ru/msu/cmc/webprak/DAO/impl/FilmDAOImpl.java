package ru.msu.cmc.webprak.DAO.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.webprak.DAO.FilmDAO;
import ru.msu.cmc.webprak.models.Film;
//import ru.msu.cmc.webprak.utils.HibernateUtil;

import java.util.List;

@Repository
public class FilmDAOImpl extends CommonDAOImpl<Film, Long> implements FilmDAO {

    public FilmDAOImpl() {
        super(Film.class);
    }

    @Override
    public List<Film> getFilmsByName(String filmName) {
        Session session = sessionFactory.openSession();
        Query<Film> query = session.createQuery("FROM Film WHERE name LIKE :gotName", Film.class)
                .setParameter("gotName", "%" + filmName + "%");
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList();
    }

    @Override
    public Film getFilmByName(String filmName) {
        Session session = sessionFactory.openSession();
        Query<Film> query = session.createQuery("FROM Film WHERE name LIKE :gotName", Film.class)
                .setParameter("gotName", "%" + filmName + "%");
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList().get(0);
    }

}