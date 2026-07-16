package br.com.capoeiramundial.entity;
import jakarta.persistence.*; import jakarta.validation.constraints.*; import lombok.*; import java.time.*;
@Entity @Table(name="videos") @Getter @Setter @NoArgsConstructor
public class Video { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; @NotBlank private String title; @Column(length=3000) private String description; private String youtubeUrl; private String fileUrl; private String thumbnailUrl; private LocalDate publishedAt=LocalDate.now(); public String getFileUrl(){return normalize(fileUrl);} public String getThumbnailUrl(){return normalize(thumbnailUrl);} private String normalize(String value){return value!=null&&value.startsWith("/uploads/")?value.substring(1):value;} }
