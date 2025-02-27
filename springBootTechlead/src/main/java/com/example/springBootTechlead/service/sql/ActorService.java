package com.example.springBootTechlead.service.sql;

import com.example.springBootTechlead.model.dto.mysql.Level1.DTOL1E1;
import com.example.springBootTechlead.model.dto.mysql.Level1.DTOL1E7;
import com.example.springBootTechlead.model.dto.mysql.Level2.DTOL2E4;
import com.example.springBootTechlead.model.dto.mysql.Level2.DTOL2E6;
import com.example.springBootTechlead.model.dto.mysql.Level2.DTOL2E7;
import com.example.springBootTechlead.model.dto.mysql.Level3.DTOL3E1;
import com.example.springBootTechlead.model.dto.mysql.Level3.DTOL3E10;
import com.example.springBootTechlead.model.dto.mysql.Level3.DTOL3E2;
import com.example.springBootTechlead.model.dto.mysql.Level3.DTOL3E6;

import java.util.List;

public interface ActorService {
    List<DTOL1E1> findFirstAndLastName();
    List<DTOL1E7> findNameAppearingMoreThan20Films();
    List<DTOL2E4> findActorAppearedAtLeast1FilmInEachCategory();
    List<DTOL2E6> findTotalRevenueByEachActor();
    List<DTOL2E7> findActorApearedAtLeast1FilmRatingRButNotG();
    List<DTOL3E1> findAverageRentalDuration();
    List<DTOL3E2> findActorAppearedFilmRatingRAnd2HoursButNotG();
    List<DTOL3E6> findActorAppearedAtLeast1FilmWithEveryOthers();
    List<DTOL3E10> findActorAppearedFilmRatingPGAndThan2HoursAndRLessThan90Minutes();
}
