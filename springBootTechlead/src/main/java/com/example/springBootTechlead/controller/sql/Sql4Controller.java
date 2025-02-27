package com.example.springBootTechlead.controller.sql;

import com.example.springBootTechlead.service.sql.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sql4")
public class Sql4Controller {
    private final FilmService filmService;
    private final CustomerService customerService;
    private final AddressService addressService;

    @Autowired
    public Sql4Controller(AddressService addressService, FilmService filmService, CustomerService customerService){
        this.filmService = filmService;
        this.customerService = customerService;
        this.addressService = addressService;
    }
    @PutMapping("/update-same-city")
    public int updateAddressForSameCityCustomers() {
        return addressService.updateAddressForSameCityCustomers();
    }

    @PutMapping("/update-horror-renters")
    public int updateEmailsForHorrorMovieRenters() {
        return customerService.updateEmailsForHorrorMovieRenters();
    }

    @PutMapping("/update-popular-films-rate")
    public int updateRentalRateForPopularFilms() {
        return filmService.updateRentalRateForPopularFilms();
    }

    @PutMapping("/update-frequent-films-duration")
    public int updateRentalDurationForFrequentFilms() {
        return filmService.updateRentalDurationForFrequentFilms();
    }

    @PutMapping("/update-old-action-films-rate")
    public int updateRentalRateForOldActionFilms() {
        return filmService.updateRentalRateForOldActionFilms();
    }

    @PutMapping("/update-frequent-customers-rate")
    public int updateRentalRateForFrequentCustomers() {
        return filmService.updateRentalRateForFrequentCustomers();
    }

    @PutMapping("/update-pg13-long-films-rate")
    public int updateRentalRateForPG13LongFilms() {
        return filmService.updateRentalRateForPG13LongFilms();
    }

    @PutMapping("/update-scifi-2010-films-duration")
    public int updateRentalDurationForSciFi2010Films() {
        return filmService.updateRentalDurationForSciFi2010Films();
    }

    @PutMapping("/update-comedy-films-rate")
    public int updateRentalRateForComedyFilms() {
        return filmService.updateRentalRateForComedyFilms();
    }

    @PutMapping("/update-short-g-films-rate")
    public int updateRentalRateForShortGFilms() {
        return filmService.updateRentalRateForShortGFilms();
    }
}
