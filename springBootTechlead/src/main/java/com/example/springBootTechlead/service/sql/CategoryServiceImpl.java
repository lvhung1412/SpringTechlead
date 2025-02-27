package com.example.springBootTechlead.service.sql;


import com.example.springBootTechlead.model.dto.mysql.Level1.DTOL1E4;
import com.example.springBootTechlead.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<DTOL1E4> findAverageRentalDuration() {
        return categoryRepository.findAverageRentalDuration();
    }
}

