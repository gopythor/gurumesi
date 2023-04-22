package com.zerobase.gurumesi.user.domain.owner;

import com.zerobase.gurumesi.user.domain.customer.CustomerDto;
import com.zerobase.gurumesi.user.domain.model.Customer;
import com.zerobase.gurumesi.user.domain.model.Owner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class OwnerDto {
    private Long id;
    private String email;

    public static OwnerDto from(Owner owner){
        return new OwnerDto(owner.getId(), owner.getEmail());
    }
}
