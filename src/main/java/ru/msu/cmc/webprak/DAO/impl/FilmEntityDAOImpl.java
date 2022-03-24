package ru.msu.cmc.webprak.DAO.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.msu.cmc.webprak.DAO.FilmEntityDAO;
import ru.msu.cmc.webprak.models.FilmEntity;
import ru.msu.cmc.webprak.utils.HibernateUtil;

import java.util.List;

public class FilmEntityDAOImpl implements FilmEntityDAO {

    @Override
    public void addFilmEntity(FilmEntity filmEntity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(filmEntity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateFilmEntity(FilmEntity filmEntity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(filmEntity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteFilmEntity(FilmEntity filmEntity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(filmEntity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public FilmEntity getFilmEntityById(Long filmEntityId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<FilmEntity> query = session.createQuery("FROM FilmEntity WHERE id = :param", FilmEntity.class)
                .setParameter("param", filmEntityId);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList().get(0);
    }

    @Override
    public List<FilmEntity> getFilmEntityAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<FilmEntity> query = session.createQuery("FROM FilmEntity", FilmEntity.class);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList();
    }
}