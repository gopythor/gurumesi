package com.zerobase.gurumesi.user.domain.model;

import com.zerobase.gurumesi.user.domain.SignUpForm;
import lombok.*;
import org.hibernate.envers.AuditOverride;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Locale;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@AuditOverride(forClass = BaseEntity.class) // Customer 업데이트 될때마다 자동으로 BaseEntity 데이터도 변경됨
public class Owner extends BaseEntity{
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

    public static Owner from(SignUpForm form){
        return Owner.builder()
                .email(form.getEmail().toLowerCase(Locale.ROOT))
                .password(form.getPassword())
                .name(form.getName())
                .birth(form.getBirth())
                .phone(form.getPhone())
                .build();
    }

}
