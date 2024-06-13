package com.cvgenerator.cvg.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "projects")
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer projectId;

    @Column(name = "project_name", nullable = false, length = 100)
    private String projectName;

    @Column(name = "role_in_project", nullable = false, length = 50)
    private String roleInProject;

    @Column(name = "description", length = 300)
    private String description;

    @Column(name = "is_running", nullable = false, length = 8)
    private Boolean isRunning;

    @Column(name = "live_Url_path", length = 100)
    private String liveUrlPath;

    @Column(name = "tech_stack_used", nullable = false, length = 100)
    private String techStackUsed;


    @ManyToOne(
            fetch = FetchType.LAZY,
            targetEntity = Experience.class
    )
    @JoinColumn(
            name = "experience_id",
            foreignKey = @ForeignKey(
                    name = "fk_project_id"
            )
    )
    private Experience experience;


}
