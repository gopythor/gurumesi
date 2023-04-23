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

    //레스토랑 추가(주인 ID, 레스토랑 정보)
    @Transactional
    public Restaurant addRestaurant(Long ownerId, AddRestaurantForm form){
        return restaurantRepository.save(Restaurant.of(ownerId, form));
    }



    // 레스토랑 리스트 보기
    @Transactional
    public List<RestaurantMapping> getList(sortEnum sort){
        //이름순
        if(sort == sortEnum.NAME){
            return restaurantRepository.findAllByOrderByRestaurantNameAsc();

        //별점순
        } else if(sort == sortEnum.STAR){
            return restaurantRepository.findAllByOrderByStarAsc();

        //주소순
        } else {
            return restaurantRepository.findAllByOrderByAddressAsc();
        }
    }

    //레스토랑 정보 보기
    @Transactional
    public Restaurant getByRestaurantId(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_RESTAURANT));
    }



}
