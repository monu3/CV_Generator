package com.cvgenerator.cvg.entity;

import com.cvgenerator.cvg.enums.SkillType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@Table(name = "skill")
@NoArgsConstructor
@AllArgsConstructor
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer skillId;
    @Enumerated(EnumType.STRING)
    private SkillType skillType;
    @Column(name = "skill_name", nullable = false, length = 30)
    private String skillName;
    @Column(name = "skill_description", nullable = false, length = 1000)
    private String skillDescription;
    @ManyToOne(
            fetch = FetchType.LAZY,
            targetEntity = BasicInformation.class
    )
    @JoinColumn(
            name = "basic_id",
            foreignKey = @ForeignKey(
                    name = "fk_skill_id"
            )
    )
    private BasicInformation basicInformation;
}
