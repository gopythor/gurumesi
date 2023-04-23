package com.zerobase.gurumesi.restaurant.controller;

import com.zerobase.gurumesi.domain.common.UserVo;
import com.zerobase.gurumesi.domain.config.JwtAuthenticationProvider;
import com.zerobase.gurumesi.restaurant.domain.model.RestaurantMapping;
import com.zerobase.gurumesi.restaurant.domain.restaurant.AddRestaurantForm;
import com.zerobase.gurumesi.restaurant.domain.restaurant.RestaurantDto;
import com.zerobase.gurumesi.restaurant.service.RestaurantService;
import com.zerobase.gurumesi.restaurant.type.sortEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
@Tag(name = "Restaurant Control", description = "Restaurant inquiry")
public class RestaurantController {
    private final JwtAuthenticationProvider provider;
    private final RestaurantService restaurantService;

    //레스토랑 추가하기
    @PostMapping
    @Operation(summary = "Owner can add his/her restaurant")
    @PreAuthorize("hasRole('ROLE_OWNER')")
    public ResponseEntity<?> addRestaurant(@RequestHeader(name = "X-AUTH-TOKEN") String token,
            @RequestBody AddRestaurantForm form) {
        UserVo vo = provider.getUserVo(token);
        return ResponseEntity.ok(
                RestaurantDto.from(restaurantService.addRestaurant(vo.getId(), form)));
    }

    //정렬로 레스토랑 보기
    @PostMapping("/list")
    @Operation(summary = "view restaurants by sort")
    public List<RestaurantMapping> inqueryRestaurantsBySort(@RequestParam(value ="sort", defaultValue ="NAME") sortEnum sort){
        return restaurantService.getList(sort);
    }

    //레스토랑 상세 정보 보기
    @GetMapping("/list")
    @Operation(summary = "view a detailed restaurant information")
   public ResponseEntity<RestaurantDto> viewRestaurantDetail(@RequestParam(value ="RestaurantID", defaultValue = "1") Long restaurantID){
        return ResponseEntity.ok(
                RestaurantDto.from(restaurantService.getByRestaurantId(restaurantID))
        );
   }




}
