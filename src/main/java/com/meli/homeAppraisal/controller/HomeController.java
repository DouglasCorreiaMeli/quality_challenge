package com.meli.homeAppraisal.controller;

import com.meli.homeAppraisal.domain.dto.request.HomeRequest;
import com.meli.homeAppraisal.domain.dto.response.HomeResponse;
import com.meli.homeAppraisal.service.HomeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/analyzeHome")
public class HomeController {

    private final HomeService service;

    public HomeController(HomeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<HomeResponse> analyzeHome(@Valid @RequestBody HomeRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(this.service.analyzeHome(request));
    }
}
