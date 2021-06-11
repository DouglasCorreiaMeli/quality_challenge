package com.meli.homeAppraisal.service;

import com.meli.homeAppraisal.domain.dto.request.HomeRequest;
import com.meli.homeAppraisal.domain.dto.request.RoomRequest;
import com.meli.homeAppraisal.domain.dto.response.HomeResponse;
import com.meli.homeAppraisal.domain.dto.response.RoomResponse;
import com.meli.homeAppraisal.domain.error.ResourceNotFoundException;
import com.meli.homeAppraisal.repository.NeighborhoodRepository;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class HomeService {

    private final NeighborhoodRepository repository;


    public HomeService(NeighborhoodRepository repository) {
        this.repository = repository;
    }

    public HomeResponse analyzeHome(HomeRequest request) {

        var response =  HomeResponse
                .builder()
                .propertyValue(this.calculatePropertyValue(request.getRooms(),request.getPropDistrict()))
                .biggestRoom(this.findBiggestRoom(request.getRooms()))
                .roomsSquareMeters(this.calculateRoomsSquareMeters(request.getRooms()))
                .totalSquareMeters(this.sumTotalSquareMeters(request.getRooms()))
                .build();
        return this.responseRounded(response);
    }

    private HomeResponse responseRounded(HomeResponse response) {
        DecimalFormat df = new DecimalFormat("###.##");
        response.setPropertyValue(Double.parseDouble(df.format(response.getPropertyValue())));
        response.setTotalSquareMeters(Double.parseDouble(df.format(response.getTotalSquareMeters())));
        response.getBiggestRoom().setSquareMeters(Double.parseDouble(df.format(response.getBiggestRoom().getSquareMeters())));
        response.getRoomsSquareMeters().forEach(r -> r.setSquareMeters(Double.parseDouble(df.format(r.getSquareMeters()))));
        return response;

    }


    public double sumTotalSquareMeters(List<RoomRequest> rooms) {
        return this.calculateRoomsSquareMeters(rooms)
                .stream()
                .mapToDouble(RoomResponse::getSquareMeters)
                .sum();
    }

    public List<RoomResponse> calculateRoomsSquareMeters(List<RoomRequest> rooms) {
        List<RoomResponse> roomResponseList = new ArrayList <>();
        rooms.forEach(roomRequest -> roomResponseList.add(RoomResponse
                                .builder()
                                .roomName(roomRequest.getRoomName())
                                .squareMeters(roomRequest.getRoomLength() * roomRequest.getRoomWidth())
                                .build()));
        return roomResponseList;
    }

    public RoomResponse findBiggestRoom(List<RoomRequest> rooms) {
        return this.calculateRoomsSquareMeters(rooms).
                stream()
                .max(Comparator.comparing(RoomResponse::getSquareMeters))
                .orElseThrow( () -> new ResourceNotFoundException("não há cômodos"));
    }

    public double calculatePropertyValue(List<RoomRequest> rooms, String propDistrict) {

        double valuePerSquareMeter  = Optional.ofNullable(repository.getDistricts().get(propDistrict))
                .orElseThrow(() -> new ResourceNotFoundException("Bairro não encontrado"));

        return this.calculateRoomsSquareMeters(rooms)
                .stream()
                .mapToDouble(r -> r.getSquareMeters() * valuePerSquareMeter)
                .sum();
    }
}
