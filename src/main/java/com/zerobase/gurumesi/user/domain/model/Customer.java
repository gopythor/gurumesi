package com.zerobase.gurumesi.user.domain.model;

import com.zerobase.gurumesi.domain.common.UserType;
import com.zerobase.gurumesi.user.domain.SignUpForm;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.envers.AuditOverride;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@AuditOverride(forClass = BaseEntity.class) // Customer 업데이트 될때마다 자동으로 BaseEntity 데이터도 변경됨

public class Customer extends BaseEntity implements UserDetails{
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String name;
    private String password;
    private String phone;
    private LocalDate birth;
    private LocalDateTime verifyExpiredAt;
    private String verificationCode;
    private boolean verify;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "role")
    private List<String> roles = new ArrayList<>();


    public static Customer from(SignUpForm form){
        return Customer.builder()
                .email(form.getEmail().toLowerCase(Locale.ROOT))
                .password(form.getPassword())
                .name(form.getName())
                .birth(form.getBirth())
                .phone(form.getPhone())
                .verify(false)
                .roles(Collections.singletonList(UserType.ROLE_CUSTOMER.name()))
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
