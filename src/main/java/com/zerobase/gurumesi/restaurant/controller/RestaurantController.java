package com.zerobase.gurumesi.restaurant.controller;

import com.zerobase.gurumesi.restaurant.domain.model.Restaurant;
import com.zerobase.gurumesi.restaurant.domain.model.RestaurantMapping;
import com.zerobase.gurumesi.restaurant.domain.restaurant.AddRestaurantForm;
import com.zerobase.gurumesi.restaurant.domain.restaurant.RestaurantDto;
import com.zerobase.gurumesi.restaurant.service.RestaurantService;
import com.zerobase.gurumesi.restaurant.type.sortEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/list")
    public List<RestaurantMapping> inqueryRestaurantsBySort(@RequestParam(value ="sort", defaultValue ="NAME") sortEnum sort){
        return restaurantService.getList(sort);
    }
//    @GetMapping("/detail")
//    public List<Restaurant> viewRestaurantDetail(@RequestParam(value ="RestaurantID", defaultValue = "1") int restaurantID){
//        return restaurantService.getList(sort);
//    }


}
