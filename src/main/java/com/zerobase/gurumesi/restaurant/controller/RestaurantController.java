package com.zerobase.gurumesi.restaurant.controller;

import com.zerobase.gurumesi.restaurant.domain.restaurant.AddRestaurantForm;
import com.zerobase.gurumesi.restaurant.domain.restaurant.RegisterForm;
import com.zerobase.gurumesi.restaurant.domain.restaurant.RestaurantDto;
import com.zerobase.gurumesi.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<?> addRestaurant(@RequestBody AddRestaurantForm form, @RequestParam Long ownerId) {
        System.out.println(form+""+ownerId);

        return ResponseEntity.ok(
                RestaurantDto.from(restaurantService.addRestaurant(ownerId, form)));
    }

}
