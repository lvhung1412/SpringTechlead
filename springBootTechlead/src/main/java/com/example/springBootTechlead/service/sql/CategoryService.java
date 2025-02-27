package com.example.springBootTechlead.service.sql;

import com.example.springBootTechlead.model.dto.mysql.Level1.DTOL1E4;
import java.util.List;

public interface CategoryService {
    List<DTOL1E4> findAverageRentalDuration();
}

