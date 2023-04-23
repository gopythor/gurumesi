package com.zerobase.gurumesi.user.service.owner;

import com.zerobase.gurumesi.exception.CustomException;
import com.zerobase.gurumesi.exception.ErrorCode;
import com.zerobase.gurumesi.user.domain.model.Owner;
import com.zerobase.gurumesi.user.domain.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnerService implements UserDetailsService {

    private final OwnerRepository ownerRepository;

    public Optional<Owner> findByIdAndEmail(Long id, String email){
        return ownerRepository.findByIdAndEmail(id,email);
    }

    public Optional<Owner> findValidOwner(String email, String password){
        return ownerRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public UserDetails loadUserByUsername(String userPK) throws UsernameNotFoundException {
        return this.ownerRepository.findByEmail(userPK)
                .orElseThrow(() -> new CustomException(ErrorCode.LOGIN_CHECK_FAIL));
    }
}
