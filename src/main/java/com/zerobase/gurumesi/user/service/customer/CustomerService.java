package com.zerobase.gurumesi.user.service.customer;

import com.zerobase.gurumesi.exception.CustomException;
import com.zerobase.gurumesi.exception.ErrorCode;
import com.zerobase.gurumesi.user.domain.model.Customer;
import com.zerobase.gurumesi.user.domain.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService implements UserDetailsService {
    private final CustomerRepository customerRepository;


    public Optional<Customer> findByIdAndEmail(Long id, String email){
        return customerRepository.findById(id)
                .stream().filter(customer->customer.getEmail().equals(email))
                .findFirst();
    }

    public Optional<Customer> findValidCustomer(String email, String password){
        return customerRepository.findByEmail(email).stream()
                .filter(customer -> customer.getPassword().equals(password))
                .findFirst();
    }

    @Override
    public UserDetails loadUserByUsername(String userPK) throws UsernameNotFoundException {
        return this.customerRepository.findByEmail(userPK)
                .orElseThrow(() -> new CustomException(ErrorCode.LOGIN_CHECK_FAIL));
    }
}
