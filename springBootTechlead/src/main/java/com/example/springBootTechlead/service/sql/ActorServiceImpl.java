package com.example.springBootTechlead.service.sql;

import com.example.springBootTechlead.model.dto.mysql.Level1.DTOL1E1;
import com.example.springBootTechlead.model.dto.mysql.Level1.DTOL1E7;
import com.example.springBootTechlead.model.dto.mysql.Level2.DTOL2E4;
import com.example.springBootTechlead.model.dto.mysql.Level2.DTOL2E6;
import com.example.springBootTechlead.model.dto.mysql.Level2.DTOL2E7;
import com.example.springBootTechlead.model.dto.mysql.Level3.*;
import com.example.springBootTechlead.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;

    @Autowired
    public ActorServiceImpl(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @Override
    public List<DTOL1E1> findFirstAndLastName() {
        return actorRepository.findFirstAndLastName();
    }

    @Override
    public List<DTOL1E7> findNameAppearingMoreThan20Films() {
        return actorRepository.findNameAppearingMoreThan20Films();
    }

    @Override
    public List<DTOL2E4> findActorAppearedAtLeast1FilmInEachCategory() {
        return actorRepository.findActorAppearedAtLeast1FilmInEachCategory();
    }

    @Override
    public List<DTOL2E6> findTotalRevenueByEachActor() {
        return actorRepository.findTotalRevenueByEachActor();
    }

    @Override
    public List<DTOL2E7> findActorApearedAtLeast1FilmRatingRButNotG() {
        return actorRepository.findActorApearedAtLeast1FilmRatingRButNotG();
    }

    @Override
    public List<DTOL3E1> findAverageRentalDuration() {
        return actorRepository.findAverageRentalDuration();
    }

    @Override
    public List<DTOL3E2> findActorAppearedFilmRatingRAnd2HoursButNotG() {
        return actorRepository.findActorAppearedFilmRatingRAnd2HoursButNotG();
    }

    @Override
    public List<DTOL3E6> findActorAppearedAtLeast1FilmWithEveryOthers() {
        return actorRepository.findActorAppearedAtLeast1FilmWithEveryOthers();
    }

    @Override
    public List<DTOL3E10> findActorAppearedFilmRatingPGAndThan2HoursAndRLessThan90Minutes() {
        return actorRepository.findActorAppearedFilmRatingPGAndThan2HoursAndRLessThan90Minutes();
    }
}
