package com.zerobase.gurumesi.restaurant.type;

import lombok.Getter;

@Getter
public enum Star {
    DUMMY(0),ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5);

    private int value;

    Star(int value){
        this.value = value;
    }
}
