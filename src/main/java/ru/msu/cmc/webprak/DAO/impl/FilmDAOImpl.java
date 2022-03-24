package ru.msu.cmc.webprak.DAO.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.msu.cmc.webprak.DAO.FilmDAO;
import ru.msu.cmc.webprak.models.Film;
import ru.msu.cmc.webprak.utils.HibernateUtil;

import java.util.List;

public class FilmDAOImpl implements FilmDAO {

    @Override
    public void addFilm(Film film) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(film);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateFilm(Film film) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(film);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteFilm(Film film) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(film);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Film> getFilmByName(String filmName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Film> query = session.createQuery("FROM Film WHERE name LIKE :gotName", Film.class)
                .setParameter("gotName", "%" + filmName + "%");
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList();
    }

    @Override
    public Film getFilmById(Long filmId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Film> query = session.createQuery("FROM Film WHERE id = :param", Film.class)
                .setParameter("param", filmId);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList().get(0);
    }

    @Override
    public List<Film> getFilmAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<Film> query = session.createQuery("FROM Film", Film.class);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList();
    }
}