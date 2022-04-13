package ru.msu.cmc.webprak.DAO.impl;

import org.springframework.stereotype.Repository;
import ru.msu.cmc.webprak.DAO.FilmEntityDAO;
import ru.msu.cmc.webprak.models.FilmEntity;

@Repository
public class FilmEntityDAOImpl extends CommonDAOImpl<FilmEntity, Long> implements FilmEntityDAO {
    public FilmEntityDAOImpl() {
        super(FilmEntity.class);
    }
}