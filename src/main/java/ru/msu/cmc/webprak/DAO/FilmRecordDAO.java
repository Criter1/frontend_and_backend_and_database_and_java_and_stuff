package ru.msu.cmc.webprak.DAO;

import ru.msu.cmc.webprak.models.FilmRecord;

import java.util.List;

public interface FilmRecordDAO extends CommonDAO<FilmRecord, Long> {
    public List<FilmRecord> getFilmRecordsByPersonID(Long id);
}