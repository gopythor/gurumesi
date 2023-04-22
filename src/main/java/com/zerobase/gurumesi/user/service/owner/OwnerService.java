package com.zerobase.gurumesi.user.service.owner;

import com.zerobase.gurumesi.user.domain.model.Owner;
import com.zerobase.gurumesi.user.domain.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public Optional<Owner> findByIdAndEmail(Long id, String email){
        return ownerRepository.findByIdAndEmail(id,email);
    }

    public Optional<Owner> findValidOwner(String email, String password){
        return ownerRepository.findByEmailAndPassword(email, password);
    }
}
