package ru.msu.cmc.webprak.DAO.impl;

import org.springframework.stereotype.Repository;
import ru.msu.cmc.webprak.DAO.FilmRecordDAO;
import ru.msu.cmc.webprak.models.FilmRecord;

@Repository
public class FilmRecordDAOImpl extends CommonDAOImpl<FilmRecord, Long> implements FilmRecordDAO {
    public FilmRecordDAOImpl() {
        super(FilmRecord.class);
    }
}