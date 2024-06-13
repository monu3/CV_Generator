package com.cvgenerator.cvg.entity;

import com.cvgenerator.cvg.enums.ContactType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ReachMeAt
 * Created On : 5/12/2024 7:52 AM
 **/
@Getter
@Setter
@Entity
@Table(name = "reach_me_at")
@NoArgsConstructor
@AllArgsConstructor
public class ReachMeAt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reachMeAtId;

    @Enumerated(EnumType.STRING)
    @Column(name = "contact_type", nullable = false, length = 20)
    private ContactType contactType;

    @Column(name = "details", nullable = false, length = 200)
    private String details;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = BasicInformation.class)
    @JoinColumn(name = "basic_id", foreignKey = @ForeignKey(name = "fk_reachmeat_id"))
    private BasicInformation basicInformation;

}
