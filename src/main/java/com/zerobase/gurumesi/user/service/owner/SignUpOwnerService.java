package com.zerobase.gurumesi.user.service.owner;

import com.zerobase.gurumesi.user.domain.SignUpForm;
import com.zerobase.gurumesi.user.domain.model.Owner;
import com.zerobase.gurumesi.user.domain.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class SignUpOwnerService {
    private final OwnerRepository ownerRepository;

    public Owner signUp(SignUpForm form) {
        return ownerRepository.save(Owner.from(form));
    }

    public boolean isEmailExist(String email) {
        return ownerRepository.findByEmail(email.toLowerCase(Locale.ROOT))
                .isPresent();
    }
}
