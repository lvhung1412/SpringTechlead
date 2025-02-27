package com.example.springBootTechlead.controller.sql;

import com.example.springBootTechlead.model.dto.mysql.Level3.*;
import com.example.springBootTechlead.service.sql.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sql3")
public class Sql3Controller {
    private final ActorService actorService;
    private final FilmService filmService;
    private final CustomerService customerService;

    @Autowired
    public Sql3Controller(ActorService actorService, FilmService filmService, CustomerService customerService){
        this.actorService = actorService;
        this.filmService = filmService;
        this.customerService = customerService;
    }
    @GetMapping("/average-rental-duration")
    public List<DTOL3E1> getAverageRentalDuration() {
        return actorService.findAverageRentalDuration();
    }

    @GetMapping("/rating-r-and-2-hours-but-not-g")
    public List<DTOL3E2> getActorsAppearedRatingGAnd2HoursButNotG() {
        return actorService.findActorAppearedFilmRatingRAnd2HoursButNotG();
    }

    @GetMapping("/rented-more-than-10-films")
    public List<DTOL3E3> getCustomersWhoRentedMoreThan10Films() {
        return customerService.getCustomersWhoRentedMoreThan10Films();
    }

    @GetMapping("/rented-every-film")
    public List<DTOL3E4> getCustomersWhoRentedEveryFilm() {
        return customerService.getCustomersWhoRentedEveryFilm();
    }

    @GetMapping("/rented-multiple-times-single-day")
    public List<DTOL3E5> getFilmsRentedBySameCustomerMultipleTimesInSingleDay() {
        return filmService.getFilmsRentedBySameCustomerMultipleTimesInSingleDay();
    }

    @GetMapping("/appeared-with-every-others")
    public List<DTOL3E6> getActorsAppearedWithEveryOthers() {
        return actorService.findActorAppearedAtLeast1FilmWithEveryOthers();
    }

    @GetMapping("/rented-at-least-one-from-each-category")
    public List<DTOL3E7> getCustomersWhoRentedAtLeastOneFromEachCategory() {
        return customerService.getCustomersWhoRentedAtLeastOneFromEachCategory();
    }

    @GetMapping("/rented-100-times-but-rating-g")
    public List<DTOL3E8> getFilmsRentedMoreThan100TimesButCustomerRentedFilmRatingG() {
        return filmService.getFilmsRentedMoreThan100TimesButCustomerRentedFilmRatingG();
    }

    @GetMapping("/never-rented-before-and-less-than-3-hours")
    public List<DTOL3E9> getCustomersWhoNeverRentedBeforeAndLessThan3Hours() {
        return customerService.getCustomersWhoNeverRentedBeforeAndLessThan3Hours();
    }

    @GetMapping("/rating-pg-and-2-hours-and-r-less-than-90-minutes")
    public List<DTOL3E10> getActorsAppearedRatingPGAnd2HoursAndRLessThan90Minutes() {
        return actorService.findActorAppearedFilmRatingPGAndThan2HoursAndRLessThan90Minutes();
    }
}
