package ru.msu.cmc.webprak.DAO.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.webprak.DAO.FilmRecordDAO;
import ru.msu.cmc.webprak.models.FilmRecord;

import java.util.List;

@Repository
public class FilmRecordDAOImpl extends CommonDAOImpl<FilmRecord, Long> implements FilmRecordDAO {
    public FilmRecordDAOImpl() {
        super(FilmRecord.class);
    }

    @Override
    public List<FilmRecord> getFilmRecordsByPersonID(Long id) {
        Session session = sessionFactory.openSession();
        Query<FilmRecord> query = session.createQuery("FROM FilmRecord WHERE id_person = " + String.valueOf(id), FilmRecord.class);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList();
    }
}