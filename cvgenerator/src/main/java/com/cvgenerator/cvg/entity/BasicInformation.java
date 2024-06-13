package com.cvgenerator.cvg.entity;

import com.cvgenerator.cvg.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@Table(name = "basic_information")
@NoArgsConstructor
@AllArgsConstructor
public class BasicInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;

    @Column(name = "middle_name", length = 30)
    private String middleName;

    @Column(name = "last_name", nullable = false, length = 30)
    private String lastName;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(length = 6, nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "religion", length = 20)
    private String religion;

    @Column(name = "nationality", nullable = false, length = 30)
    private String nationality;

    @Column(name = "current_address", nullable = false, length = 150)
    private String currentAddress;

    @Column(name = "background", nullable = false, length = 1000)
    private String background;

    @Column(name = "photo_path", nullable = false, length = 200)
    private String photoPath;

    @OneToMany(mappedBy = "basicInformation", cascade = CascadeType.ALL)
    private List<EducationInformation> educationInformation;

    @OneToMany(mappedBy = "basicInformation", cascade = CascadeType.ALL)
    private List<Experience> experience;

    @OneToMany(mappedBy = "basicInformation", cascade = CascadeType.ALL)
    private List<ReachMeAt> reachMeAt;

    @OneToMany(mappedBy = "basicInformation", cascade = CascadeType.ALL)
    private List<Skill> skill;

}
