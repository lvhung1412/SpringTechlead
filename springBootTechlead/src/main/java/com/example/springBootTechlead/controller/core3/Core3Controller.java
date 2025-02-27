package com.example.springBootTechlead.controller.core3;

import com.example.springBootTechlead.service.core3.Core3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/core3")
public class Core3Controller {

    @Autowired
    Core3Service core3Service;

    // Tạo class để ánh xạ JSON
    static class ExpressionRequest {
        public String expression;
    }

    @PostMapping("")
    public int core3(@RequestBody ExpressionRequest request) {
        return core3Service.getEvaluate(request.expression);
    }
}
