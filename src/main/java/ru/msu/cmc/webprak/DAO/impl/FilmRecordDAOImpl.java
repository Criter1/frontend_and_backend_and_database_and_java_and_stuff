package ru.msu.cmc.webprak.DAO.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.msu.cmc.webprak.DAO.FilmRecordDAO;
import ru.msu.cmc.webprak.models.FilmRecord;
import ru.msu.cmc.webprak.utils.HibernateUtil;

import java.util.List;

public class FilmRecordDAOImpl implements FilmRecordDAO {

    @Override
    public void addFilmRecord(FilmRecord filmRecord) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(filmRecord);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateFilmRecord(FilmRecord filmRecord) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(filmRecord);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteFilmRecord(FilmRecord filmRecord) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(filmRecord);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public FilmRecord getFilmRecordById(Long filmRecordId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<FilmRecord> query = session.createQuery("FROM FilmRecord WHERE id = :param", FilmRecord.class)
                .setParameter("param", filmRecordId);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList().get(0);
    }

    @Override
    public List<FilmRecord> getFilmRecordAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query<FilmRecord> query = session.createQuery("FROM FilmRecord", FilmRecord.class);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList();
    }
}