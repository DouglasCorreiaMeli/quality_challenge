package com.meli.homeAppraisal.helpers;

import com.meli.homeAppraisal.domain.dto.request.HomeRequest;
import com.meli.homeAppraisal.domain.dto.request.RoomRequest;
import com.meli.homeAppraisal.domain.dto.response.RoomResponse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TestUtilsGenerator {

    public static HomeRequest getSimpleRequest() {
        return HomeRequest
                .builder()
                .propDistrict("some district")
                .propName("some house")
                .rooms(createListRoomRequest())
                .build();
    }

    public static Double getTotalSquareMetersForSimpleRequest() {
        return 50.0;
    }

    private static List<RoomRequest> createListRoomRequest() {
        List<RoomRequest> roomRequests = new ArrayList <>();
        roomRequests.add(RoomRequest.builder().roomName("room 1").roomLength(3.0).roomWidth(3.0).build());
        roomRequests.add(RoomRequest.builder().roomName("room 2").roomLength(4.0).roomWidth(4.0).build());
        roomRequests.add(RoomRequest.builder().roomName("room 3").roomLength(5.0).roomWidth(5.0).build());
        return roomRequests;
    }


    public static RoomResponse getSimpleRequestBiggestRoom() {

        var biggest = getSimpleRequest()
                .getRooms()
                .stream()
                .max(Comparator.comparing(r -> r.getRoomLength() * r.getRoomWidth()))
                .get();

        return RoomResponse
                .builder()
                .roomName(biggest.getRoomName())
                .squareMeters(biggest.getRoomLength()* biggest.getRoomWidth())
                .build();
    }

    public static List<RoomResponse> getListRoomResponseBySimpleRequest() {
        List<RoomResponse> list = new ArrayList <>();
        getSimpleRequest().getRooms().forEach(roomRequest ->
            list.add(RoomResponse
                    .builder()
                    .roomName(roomRequest.getRoomName())
                    .squareMeters(roomRequest.getRoomLength()*roomRequest.getRoomWidth())
                    .build())
        );
        return list;
    }
}
