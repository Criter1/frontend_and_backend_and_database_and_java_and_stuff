package ru.msu.cmc.webprak.DAO;

import ru.msu.cmc.webprak.models.FilmRecord;

import java.util.List;

public interface FilmRecordDAO {
    void addFilmRecord(FilmRecord filmRecord);
    void updateFilmRecord(FilmRecord filmRecord);
    void deleteFilmRecord(FilmRecord filmRecord);

    FilmRecord getFilmRecordById(Long filmRecordId);
    List<FilmRecord> getFilmRecordAll();
}