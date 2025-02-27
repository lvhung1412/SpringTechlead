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
import com.example.springBootTechlead.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<DTOL1E5> getCustomersWhoRentedInJan2022() {
        return customerRepository.findNameAndAddressWhoRentedFilmOnJanuary2022();
    }

    @Override
    public List<DTOL2E1> getTop10CustomersByRevenue() {
        return customerRepository.findTop10HavingMostRevenue();
    }

    @Override
    public List<DTOL2E2> getCustomersWhoRentedAllCategories() {
        return customerRepository.findCustomerRentedFilmInAllCategory();
    }

    @Override
    public List<DTOL2E5> getCustomersWhoRentedSameFilmMultipleTimes() {
        return customerRepository.findCustomerRentedSameFilmMoreThanOnce();
    }

    @Override
    public List<DTOL2E9> getCustomersWhoRentedNewFilms() {
        return customerRepository.findCustomersRentedFilmThatNeverRentedBefore();
    }

    @Override
    public List<DTOL3E3> getCustomersWhoRentedMoreThan10Films() {
        return customerRepository.findCustomerRentedThan10Films();
    }

    @Override
    public List<DTOL3E4> getCustomersWhoRentedEveryFilm() {
        return customerRepository.findCustomerRentedEveryFilm();
    }

    @Override
    public List<DTOL3E7> getCustomersWhoRentedAtLeastOneFromEachCategory() {
        return customerRepository.findCustomerRentedAtLeast1FilmFromEachCategory();
    }

    @Override
    public List<DTOL3E9> getCustomersWhoNeverRentedBeforeAndLessThan3Hours() {
        return customerRepository.findCustomerRentedFilmThatNeverRentedBeforeAndNeverThan3Hours();
    }

    @Override
    @Transactional
    public int updateEmailsForHorrorMovieRenters() {
        return customerRepository.updateCustomerEmailForHorrorRentals();
    }
}
