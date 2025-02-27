package com.example.springBootTechlead.controller.core4;

import com.example.springBootTechlead.service.core4.Core4Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/core4")
public class Core4Controller {

    @Autowired
    private Core4Service core4Service;

    static class FileRequest {
        public String filePath;
    }

    @PostMapping("")
    public LinkedHashMap<String, LinkedHashMap<String, List<LinkedHashMap<String, Double>>>> core4(@RequestBody FileRequest request) {
        return core4Service.printInformation(request.filePath);
    }

}
