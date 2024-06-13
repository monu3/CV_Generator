package com.cvgenerator.cvg.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * ContactUs
 * Created On : 5/28/2024 6:18 PM
 **/
@Getter
@Setter
@Builder
@Entity
@Table(name = "contact_us")
@NoArgsConstructor
@AllArgsConstructor
public class ContactUs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;

    @Column(name = "middle_name", length = 30)
    private String middleName;

    @Column(name = "last_name", nullable = false, length = 30)
    private String lastName;

    @Column(name = "address", nullable = false, length = 100)
    private String address;

    @Column(name = "phone_number", nullable = false, length = 10)
    private String phoneNumber;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "isActive", nullable = false)
    private Boolean isActive;


}
