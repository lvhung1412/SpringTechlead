package com.example.springBootTechlead.controller.sql;

import com.example.springBootTechlead.model.dto.mysql.Level1.*;
import com.example.springBootTechlead.service.sql.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sql1")
public class Sql1Controller {
    private final ActorService actorService;
    private final FilmService filmService;
    private final CategoryService categoryService;
    private final CustomerService customerService;
    private final StoreService storeService;

    @Autowired
    public Sql1Controller(ActorService actorService, FilmService filmService, CustomerService customerService, CategoryService categoryService, StoreService storeService){
        this.actorService = actorService;
        this.filmService = filmService;
        this.categoryService = categoryService;
        this.customerService = customerService;
        this.storeService = storeService;
    }

    @GetMapping("/first-and-last-name")
    public List<DTOL1E1> getFirstAndLastName() {
        return actorService.findFirstAndLastName();
    }

    @GetMapping("/titles-rental-rate")
    public List<DTOL1E2> getTitlesAndRentalRate() {
        return filmService.getTitlesAndRentalRate();
    }

    @GetMapping("/top-five")
    public List<DTOL1E3> getTopFiveFilms() {
        return filmService.getTopFiveFilms();
    }

    @GetMapping("/average-rental-duration")
    public List<DTOL1E4> findAverageRentalDuration() {
        return categoryService.findAverageRentalDuration();
    }

    @GetMapping("/rented-in-jan-2022")
    public List<DTOL1E5> getCustomersWhoRentedInJan2022() {
        return customerService.getCustomersWhoRentedInJan2022();
    }

    @GetMapping("/revenue-2021")
    public List<DTOL1E6> getRevenue2021() {
        return storeService.getRevenue2021();
    }

    @GetMapping("/appeared-more-than-20-films")
    public List<DTOL1E7> getActorsAppearedMoreThan20Films() {
        return actorService.findNameAppearingMoreThan20Films();
    }

    @GetMapping("/pg13-longer-than-120")
    public List<DTOL1E8> getTitlesRatingPG13andMoreThan120() {
        return filmService.getTitlesRatingPG13andMoreThan120();
    }



}
