package ru.msu.cmc.webprak.DAO.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.msu.cmc.webprak.DAO.FilmEntityDAO;
import ru.msu.cmc.webprak.models.FilmEntity;

import java.util.List;

@Repository
public class FilmEntityDAOImpl extends CommonDAOImpl<FilmEntity, Long> implements FilmEntityDAO {
    public FilmEntityDAOImpl() {
        super(FilmEntity.class);
    }

    @Override
    public List<FilmEntity> getFilmEntitiesByFilmID(Long id) {
        Session session = sessionFactory.openSession();
        Query<FilmEntity> query = session.createQuery("FROM FilmEntity WHERE film_id = " + String.valueOf(id), FilmEntity.class);
        if (query.getResultList().size() == 0) {
            return null;
        }
        return query.getResultList();
    }

}