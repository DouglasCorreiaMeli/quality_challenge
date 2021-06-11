package com.meli.homeAppraisal.repository;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class NeighborhoodRepository {

    private final HashMap<String, Double> districts = new HashMap <>();

    public NeighborhoodRepository() {
        // the key is district name
        // the value is R$ per mˆ2
        this.districts.put("vila olímpia", 12310.0);
        this.districts.put("itaim bibi", 11570.0);
        this.districts.put("pinheiros", 11250.0);
        this.districts.put("vila madalena", 11340.0);
        this.districts.put("moóca", 6860.0);
        this.districts.put("tatuapé", 6800.0);
        this.districts.put("santana", 6810.0);
        this.districts.put("parada Inglesa", 6790.0);
    }

    public HashMap <String, Double> getDistricts() {
        return districts;
    }
}
