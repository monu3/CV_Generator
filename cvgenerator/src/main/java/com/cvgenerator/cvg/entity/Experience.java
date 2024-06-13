package com.cvgenerator.cvg.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "experience")
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer experienceId;

    @Column(name = "company_name", nullable = false, length = 100)
    private String companyName;

    @Column(name = "company_website", nullable = false, length = 100)
    private String companyWebsite;

    @Column(name = "company_address", nullable = false, length = 100)
    private String address;

    @Column(name = "contact", nullable = false, length = 10)
    private String contact;

    @Column(name = "start_date", nullable = false, length = 100)
    private LocalDate startDate;

    @Column(name = "end_date", length = 100)
    private LocalDate endDate;

    @Column(name = "position", nullable = false, length = 100)
    private String position;

    @Column(name = "job_role", nullable = false, length = 100)
    private String jobRole;

    @Column(name = "is_current", nullable = false, length = 100)
    private Boolean isCurrent;


    @ManyToOne(fetch = FetchType.LAZY, targetEntity = BasicInformation.class)

    @JoinColumn(name = "basic_id", foreignKey = @ForeignKey(name = "fk_experience_id"))
    private BasicInformation basicInformation;

    @OneToMany(mappedBy = "experience", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Projects> projects;


}
