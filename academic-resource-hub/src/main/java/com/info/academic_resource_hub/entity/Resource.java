package com.info.academic_resource_hub.entity;


import com.info.academic_resource_hub.enums.ResourceType;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String fileName;

    private String filePath;

    private Long fileSize;

    @Enumerated(EnumType.STRING)
    private ResourceType type;

    private LocalDateTime uploadedAt;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "uploaded_by")
    private User uploadedBy;
}
