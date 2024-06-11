package com.Thienbao.booking.controller;

import com.Thienbao.booking.dto.HotelDTO;
import com.Thienbao.booking.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private  HotelService hotelService;


    @GetMapping("")
    public ResponseEntity<?> getAllHotels() {
        List<HotelDTO> hotelDTOs = hotelService.getAllHotels();
        return new ResponseEntity<>(hotelDTOs, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> insertHotels(@RequestBody HotelDTO hotelDTO) {
        HotelDTO newHotel = hotelService.addHotel(hotelDTO);
        return new ResponseEntity<>(newHotel, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteHotel(@PathVariable int id) {
        hotelService.deleteHotel(id);
        return new ResponseEntity<>("Xoa thanh cong",HttpStatus.OK);
    }



    @PutMapping("/update/{id}")
    public ResponseEntity<HotelDTO> updateHotel(@PathVariable int id, @RequestBody HotelDTO hotelDTO) {
        HotelDTO updatedHotel = hotelService.updateHotel(id, hotelDTO);
        return new ResponseEntity<>(updatedHotel, HttpStatus.OK);

    }
}


