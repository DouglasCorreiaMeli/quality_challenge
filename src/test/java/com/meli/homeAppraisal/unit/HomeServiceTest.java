package com.meli.homeAppraisal.unit;

import com.meli.homeAppraisal.domain.dto.request.HomeRequest;
import com.meli.homeAppraisal.domain.dto.request.RoomRequest;
import com.meli.homeAppraisal.domain.dto.response.RoomResponse;
import com.meli.homeAppraisal.domain.error.ResourceNotFoundException;
import com.meli.homeAppraisal.helpers.TestUtilsGenerator;
import com.meli.homeAppraisal.repository.NeighborhoodRepository;
import com.meli.homeAppraisal.service.HomeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class HomeServiceTest {

    @Mock
    NeighborhoodRepository repository;

    @InjectMocks
    HomeService service;

    HomeRequest request;

    @BeforeEach
    void init(){
        this.request = TestUtilsGenerator.getSimpleRequest();
    }

    @Test
    public void totalSquareMetersWellCalculatedTest() {

        //arrange
        double expectedValue = TestUtilsGenerator.getTotalSquareMetersForSimpleRequest();
        //act
        double receivedValue = this.service.sumTotalSquareMeters(this.request.getRooms());
        //assert
        assertEquals(expectedValue, receivedValue);

    }

    @Test
    public void districtShouldByFoundAndReturnPropertyValueTest() {

        //arrange
        double valuePerSquareMetersInDistrict = 1000.0;
        HashMap<String, Double> districts = new HashMap <>();
        districts.put(this.request.getPropDistrict(), valuePerSquareMetersInDistrict);
        when(repository.getDistricts()).thenReturn(districts);
        double expectedValue = TestUtilsGenerator.getTotalSquareMetersForSimpleRequest() * valuePerSquareMetersInDistrict;
        //act
        double receivedValue = this.service.calculatePropertyValue(this.request.getRooms(),this.request.getPropDistrict());
        //assert
        assertEquals(expectedValue,receivedValue);
    }

    @Test
    public void districtShouldNotByFoundAndReturnExceptionTest() {

        // act and assert
        assertThrows(ResourceNotFoundException.class, () ->
                this.service.calculatePropertyValue(this.request.getRooms(), this.request.getPropDistrict()));
    }

    @Test
    public  void biggestRoomMustBeReturnedTest(){

        //arrange
        RoomResponse expectedBiggestRoom = TestUtilsGenerator.getSimpleRequestBiggestRoom();

        //act
        RoomResponse receivedBiggestRoom = service.findBiggestRoom(this.request.getRooms());

        //assert
        assertEquals(expectedBiggestRoom, receivedBiggestRoom);
    }

    @Test
    public  void totalSquareMetersByRoomWellCalculatedTest(){

        //arrange
        var expectedList = TestUtilsGenerator.getListRoomResponseBySimpleRequest();
        //act
        var receivedList = service.calculateRoomsSquareMeters(this.request.getRooms());
        //assert
        assertEquals(expectedList, receivedList);
    }

}


