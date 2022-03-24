package ru.msu.cmc.webprak.utils;

import ru.msu.cmc.webprak.DAO.PersonDAO;
import ru.msu.cmc.webprak.DAO.FilmDAO;
import ru.msu.cmc.webprak.DAO.FilmEntityDAO;
import ru.msu.cmc.webprak.DAO.FilmRecordDAO;
import ru.msu.cmc.webprak.DAO.impl.PersonDAOImpl;
import ru.msu.cmc.webprak.DAO.impl.FilmDAOImpl;
import ru.msu.cmc.webprak.DAO.impl.FilmEntityDAOImpl;
import ru.msu.cmc.webprak.DAO.impl.FilmRecordDAOImpl;

public class DAOFactory {

    private static PersonDAO personDAO = null;
    private static FilmDAO filmDAO = null;
    private static FilmEntityDAO filmEntityDAO = null;
    private static FilmRecordDAO filmRecordDAO = null;
    private static DAOFactory instance = null;

    public static synchronized DAOFactory getInstance(){
        if (instance == null){
            instance = new DAOFactory();
        }
        return instance;
    }

    public PersonDAO getPersonDAO(){
        if (personDAO == null){
            personDAO = new PersonDAOImpl();
        }
        return personDAO;
    }

    public FilmDAO getFilmDAO(){
        if (filmDAO == null){
            filmDAO = new FilmDAOImpl();
        }
        return filmDAO;
    }

    public FilmEntityDAO getfilmEntityDAO(){
        if (filmEntityDAO == null){
            filmEntityDAO = new FilmEntityDAOImpl();
        }
        return filmEntityDAO;
    }

    public FilmRecordDAO getFilmRecordDAO(){
        if (filmRecordDAO == null){
            filmRecordDAO = new FilmRecordDAOImpl();
        }
        return filmRecordDAO;
    }
}