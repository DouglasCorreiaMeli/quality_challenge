package com.meli.homeAppraisal.repository;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class NeighborhoodRepository {

    private final HashMap<String, Double> districts = new HashMap <>();

    public NeighborhoodRepository() {
        // the key is district name
        // the value is R$ per mˆ2
        this.districts.put("Vila Olímpia", 12310.0);
        this.districts.put("Itaim Bibi", 11570.0);
        this.districts.put("Pinheiros", 11250.0);
        this.districts.put("Vila Madalena", 11340.0);
        this.districts.put("Moóca", 6860.0);
        this.districts.put("Tatuapé", 6800.0);
        this.districts.put("Santana", 6810.0);
        this.districts.put("Parada Inglesa", 6790.0);
    }

    public HashMap <String, Double> getDistricts() {
        return districts;
    }
}
