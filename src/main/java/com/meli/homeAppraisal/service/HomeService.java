package com.meli.homeAppraisal.service;

import com.meli.homeAppraisal.domain.dto.request.HomeRequest;
import com.meli.homeAppraisal.domain.dto.request.RoomRequest;
import com.meli.homeAppraisal.domain.dto.response.HomeResponse;
import com.meli.homeAppraisal.domain.dto.response.RoomResponse;
import com.meli.homeAppraisal.repository.NeighborhoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeService {

    private final NeighborhoodRepository repository;

    public HomeService(NeighborhoodRepository repository) {
        this.repository = repository;
    }

    public HomeResponse analyzeHome(HomeRequest request) {
        return HomeResponse
                .builder()
                .propertyValue(this.calculatePropertyValue(request.getRooms(),request.getPropDistrict()))
                .biggestRoom(this.findBiggestRoom(request.getRooms()))
                .roomsSquareMeters(this.calculateRoomsSquareMeters(request.getRooms()))
                .build();
    }

    private List<RoomResponse> calculateRoomsSquareMeters(List<RoomRequest> rooms) {
        return null;
    }

    public RoomResponse findBiggestRoom(List<RoomRequest> rooms) {
        return null;
    }

    public Double calculatePropertyValue(List<RoomRequest> rooms, String propDistrict) {
        return null;
    }
}
