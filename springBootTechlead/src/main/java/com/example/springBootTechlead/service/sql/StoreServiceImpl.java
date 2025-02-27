package com.example.springBootTechlead.service.sql;


import com.example.springBootTechlead.model.dto.mysql.Level1.DTOL1E6;
import com.example.springBootTechlead.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public List<DTOL1E6> getRevenue2021() {
        return storeRepository.findRevenue2021();
    }
}

