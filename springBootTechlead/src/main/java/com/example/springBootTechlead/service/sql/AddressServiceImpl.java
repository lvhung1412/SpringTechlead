package com.example.springBootTechlead.service.sql;


import com.example.springBootTechlead.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    @Transactional
    public int updateAddressForSameCityCustomers() {
        return addressRepository.updateAddressForSameCityCustomers();
    }
}

