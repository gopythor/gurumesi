package com.zerobase.gurumesi.restaurant.service;

import com.zerobase.gurumesi.exception.CustomException;
import com.zerobase.gurumesi.exception.ErrorCode;
import com.zerobase.gurumesi.restaurant.domain.model.RestaurantMapping;
import com.zerobase.gurumesi.restaurant.domain.restaurant.AddRestaurantForm;
import com.zerobase.gurumesi.restaurant.domain.model.Restaurant;
import com.zerobase.gurumesi.restaurant.domain.model.repository.RestaurantRepository;
import com.zerobase.gurumesi.restaurant.type.sortEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Transactional
    public Restaurant addRestaurant(Long ownerId, AddRestaurantForm form){
        return restaurantRepository.save(Restaurant.of(ownerId, form));
    }



    @Transactional
    public List<RestaurantMapping> getList(sortEnum sort){
        if(sort == sortEnum.NAME){
            return restaurantRepository.findAllByOrderByRestaurantNameAsc();
        } else if(sort == sortEnum.STAR){
            return restaurantRepository.findAllByOrderByStarAsc();
        } else {
            return restaurantRepository.findAllByOrderByAddressAsc();
        }
    }
    @Transactional
    public Restaurant getByRestaurantId(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESTAURANT));
    }



}
