package com.zerobase.gurumesi.user.service.customer;

import com.zerobase.gurumesi.user.domain.SignUpForm;
import com.zerobase.gurumesi.user.domain.model.Customer;
import com.zerobase.gurumesi.user.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class SignUpCustomerService {
    private final CustomerRepository customerRepository;

    public Customer signUp(SignUpForm form) {
        return customerRepository.save(Customer.from(form));
    }

    public boolean isEmailExist(String email) {
        return customerRepository.findByEmail(email.toLowerCase(Locale.ROOT))
                .isPresent();
    }

}
