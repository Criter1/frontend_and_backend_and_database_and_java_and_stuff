package ru.msu.cmc.webprak.DAO;

import ru.msu.cmc.webprak.models.FilmEntity;

import java.util.List;

public interface FilmEntityDAO {
    void addFilmEntity(FilmEntity filmEntity);
    void updateFilmEntity(FilmEntity filmEntity);
    void deleteFilmEntity(FilmEntity filmEntity);

    FilmEntity getFilmEntityById(Long filmEntityId);
    List<FilmEntity> getFilmEntityAll();
}