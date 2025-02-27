package com.example.springBootTechlead.service.sql;

import com.example.springBootTechlead.model.dto.mysql.Level1.DTOL1E2;
import com.example.springBootTechlead.model.dto.mysql.Level1.DTOL1E3;
import com.example.springBootTechlead.model.dto.mysql.Level1.DTOL1E8;
import com.example.springBootTechlead.model.dto.mysql.Level2.DTOL2E10;
import com.example.springBootTechlead.model.dto.mysql.Level2.DTOL2E3;
import com.example.springBootTechlead.model.dto.mysql.Level2.DTOL2E8;
import com.example.springBootTechlead.model.dto.mysql.Level3.DTOL3E5;
import com.example.springBootTechlead.model.dto.mysql.Level3.DTOL3E8;

import java.util.List;

public interface FilmService {
    List<DTOL1E2> getTitlesAndRentalRate();
    List<DTOL1E3> getTopFiveFilms();
    List<DTOL1E8> getTitlesRatingPG13andMoreThan120();
    List<DTOL2E3> getFilmsNeverReturned();
    List<DTOL2E8> getFilmsRentedByMoreThan50Customers();
    List<DTOL2E10> getFilmsRentedByEveryCustomerWhoNeverRentedAction();
    List<DTOL3E5> getFilmsRentedBySameCustomerMultipleTimesInSingleDay();
    List<DTOL3E8> getFilmsRentedMoreThan100TimesButCustomerRentedFilmRatingG();

    int updateRentalRateForPopularFilms();
    int updateRentalDurationForFrequentFilms();
    int updateRentalRateForOldActionFilms();
    int updateRentalRateForFrequentCustomers();
    int updateRentalRateForPG13LongFilms();
    int updateRentalDurationForSciFi2010Films();
    int updateRentalRateForComedyFilms();
    int updateRentalRateForShortGFilms();
}

