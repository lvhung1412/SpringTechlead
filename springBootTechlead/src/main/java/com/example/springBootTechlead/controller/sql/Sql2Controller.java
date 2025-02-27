package com.example.springBootTechlead.controller.sql;

import com.example.springBootTechlead.model.dto.mysql.Level2.*;
import com.example.springBootTechlead.service.sql.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sql2")
public class Sql2Controller {
    private final ActorService actorService;
    private final FilmService filmService;
    private final CustomerService customerService;

    @Autowired
    public Sql2Controller(ActorService actorService, FilmService filmService, CustomerService customerService){
        this.actorService = actorService;
        this.filmService = filmService;
        this.customerService = customerService;
    }
    @GetMapping("/top-10-revenue")
    public List<DTOL2E1> getTop10CustomersByRevenue() {
        return customerService.getTop10CustomersByRevenue();
    }

    @GetMapping("/rented-all-categories")
    public List<DTOL2E2> getCustomersWhoRentedAllCategories() {
        return customerService.getCustomersWhoRentedAllCategories();
    }

    @GetMapping("/never-returned")
    public List<DTOL2E3> getFilmsNeverReturned() {
        return filmService.getFilmsNeverReturned();
    }

    @GetMapping("/appeared-in-each-category")
    public List<DTOL2E4> getActorsAppearedInEachCategory() {
        return actorService.findActorAppearedAtLeast1FilmInEachCategory();
    }

    @GetMapping("/rented-same-film-multiple-times")
    public List<DTOL2E5> getCustomersWhoRentedSameFilmMultipleTimes() {
        return customerService.getCustomersWhoRentedSameFilmMultipleTimes();
    }

    @GetMapping("/total-revenue")
    public List<DTOL2E6> getTotalRevenueByEachActor() {
        return actorService.findTotalRevenueByEachActor();
    }

    @GetMapping("/rating-r-but-not-g")
    public List<DTOL2E7> getActorsAppearedRatingRButNotG() {
        return actorService.findActorApearedAtLeast1FilmRatingRButNotG();
    }

    @GetMapping("/rented-by-50-customers")
    public List<DTOL2E8> getFilmsRentedByMoreThan50Customers() {
        return filmService.getFilmsRentedByMoreThan50Customers();
    }

    @GetMapping("/rented-new-films")
    public List<DTOL2E9> getCustomersWhoRentedNewFilms() {
        return customerService.getCustomersWhoRentedNewFilms();
    }

    @GetMapping("/rented-by-non-action-customers")
    public List<DTOL2E10> getFilmsRentedByEveryCustomerWhoNeverRentedAction() {
        return filmService.getFilmsRentedByEveryCustomerWhoNeverRentedAction();
    }
}
