package com.example.springBootTechlead.service.sql;

import com.example.springBootTechlead.model.dto.mysql.Level1.DTOL1E2;
import com.example.springBootTechlead.model.dto.mysql.Level1.DTOL1E3;
import com.example.springBootTechlead.model.dto.mysql.Level1.DTOL1E8;
import com.example.springBootTechlead.model.dto.mysql.Level2.DTOL2E10;
import com.example.springBootTechlead.model.dto.mysql.Level2.DTOL2E3;
import com.example.springBootTechlead.model.dto.mysql.Level2.DTOL2E8;
import com.example.springBootTechlead.model.dto.mysql.Level3.DTOL3E5;
import com.example.springBootTechlead.model.dto.mysql.Level3.DTOL3E8;
import com.example.springBootTechlead.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;

    @Autowired
    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public List<DTOL1E2> getTitlesAndRentalRate() {
        return filmRepository.findTiltesAndRentalRate();
    }

    @Override
    public List<DTOL1E3> getTopFiveFilms() {
        return filmRepository.findTopFiveFilm();
    }

    @Override
    public List<DTOL1E8> getTitlesRatingPG13andMoreThan120() {
        return filmRepository.findTitlesRatingPG13andMoreThan120();
    }

    @Override
    public List<DTOL2E3> getFilmsNeverReturned() {
        return filmRepository.findFilmNeverReturn();
    }

    @Override
    public List<DTOL2E8> getFilmsRentedByMoreThan50Customers() {
        return filmRepository.findFilmRentedByMoreThan50CustomesButNeverRentedBySameThan1();
    }

    @Override
    public List<DTOL2E10> getFilmsRentedByEveryCustomerWhoNeverRentedAction() {
        return filmRepository.findFilmRentedByEveryCustomerWhoNeverRentedAction();
    }

    @Override
    public List<DTOL3E5> getFilmsRentedBySameCustomerMultipleTimesInSingleDay() {
        return filmRepository.findFilmRentedBySameCustomerThanOnceInSingleDay();
    }

    @Override
    public List<DTOL3E8> getFilmsRentedMoreThan100TimesButCustomerRentedFilmRatingG() {
        return filmRepository.findFilmRentedThan100TimesButCustomerRentedFilmRatingG();
    }

    @Override
    @Transactional
    public int updateRentalRateForPopularFilms() {
        return filmRepository.updateRentalRateForPopularFilms();
    }

    @Override
    @Transactional
    public int updateRentalDurationForFrequentFilms() {
        return filmRepository.updateRentalDurationForFrequentFilms();
    }

    @Override
    @Transactional
    public int updateRentalRateForOldActionFilms() {
        return filmRepository.updateRentalRateForOldActionFilms();
    }

    @Override
    @Transactional
    public int updateRentalRateForFrequentCustomers() {
        return filmRepository.updateRentalRateForFrequentCustomers();
    }

    @Override
    @Transactional
    public int updateRentalRateForPG13LongFilms() {
        return filmRepository.updateRentalRateForPG13LongFilms();
    }

    @Override
    @Transactional
    public int updateRentalDurationForSciFi2010Films() {
        return filmRepository.updateRentalDurationForSciFi2010Films();
    }

    @Override
    @Transactional
    public int updateRentalRateForComedyFilms() {
        return filmRepository.updateRentalRateForComedyFilms();
    }

    @Override
    @Transactional
    public int updateRentalRateForShortGFilms() {
        return filmRepository.updateRentalRateForShortGFilms();
    }
}

