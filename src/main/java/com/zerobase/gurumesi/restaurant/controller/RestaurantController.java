package com.zerobase.gurumesi.restaurant.controller;

import com.zerobase.gurumesi.domain.common.UserVo;
import com.zerobase.gurumesi.domain.config.JwtAuthenticationProvider;
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
    private final JwtAuthenticationProvider provider;
    private final RestaurantService restaurantService;

    //레스토랑 추가하기
    @PostMapping
    public ResponseEntity<?> addRestaurant(@RequestHeader(name = "X-AUTH-TOKEN") String token,
            @RequestBody AddRestaurantForm form) {
        UserVo vo = provider.getUserVo(token);
        return ResponseEntity.ok(
                RestaurantDto.from(restaurantService.addRestaurant(vo.getId(), form)));
    }

    //정렬로 레스토랑 보기
    @PostMapping("/list")
    public List<RestaurantMapping> inqueryRestaurantsBySort(@RequestParam(value ="sort", defaultValue ="NAME") sortEnum sort){
        return restaurantService.getList(sort);
    }

    //레스토랑 상세 정보 보기
    @GetMapping("/list")
   public ResponseEntity<RestaurantDto> viewRestaurantDetail(@RequestParam(value ="RestaurantID", defaultValue = "1") Long restaurantID){
        return ResponseEntity.ok(
                RestaurantDto.from(restaurantService.getByRestaurantId(restaurantID))
        );
   }




}
