package com.meli.homeAppraisal.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class HomeResponse {

    double totalSquareMeters;
    double propertyValue;
    RoomResponse biggestRoom;
    List<RoomResponse> roomsSquareMeters;
}
