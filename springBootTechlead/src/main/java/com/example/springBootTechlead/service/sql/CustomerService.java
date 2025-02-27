package com.example.springBootTechlead.service.sql;

import com.example.springBootTechlead.model.dto.mysql.Level1.DTOL1E5;
import com.example.springBootTechlead.model.dto.mysql.Level2.DTOL2E1;
import com.example.springBootTechlead.model.dto.mysql.Level2.DTOL2E2;
import com.example.springBootTechlead.model.dto.mysql.Level2.DTOL2E5;
import com.example.springBootTechlead.model.dto.mysql.Level2.DTOL2E9;
import com.example.springBootTechlead.model.dto.mysql.Level3.DTOL3E3;
import com.example.springBootTechlead.model.dto.mysql.Level3.DTOL3E4;
import com.example.springBootTechlead.model.dto.mysql.Level3.DTOL3E7;
import com.example.springBootTechlead.model.dto.mysql.Level3.DTOL3E9;

import java.util.List;

public interface CustomerService {
    List<DTOL1E5> getCustomersWhoRentedInJan2022();
    List<DTOL2E1> getTop10CustomersByRevenue();
    List<DTOL2E2> getCustomersWhoRentedAllCategories();
    List<DTOL2E5> getCustomersWhoRentedSameFilmMultipleTimes();
    List<DTOL2E9> getCustomersWhoRentedNewFilms();
    List<DTOL3E3> getCustomersWhoRentedMoreThan10Films();
    List<DTOL3E4> getCustomersWhoRentedEveryFilm();
    List<DTOL3E7> getCustomersWhoRentedAtLeastOneFromEachCategory();
    List<DTOL3E9> getCustomersWhoNeverRentedBeforeAndLessThan3Hours();
    int updateEmailsForHorrorMovieRenters();
}

