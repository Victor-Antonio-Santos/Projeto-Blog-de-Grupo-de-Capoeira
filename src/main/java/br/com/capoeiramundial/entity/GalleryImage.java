package br.com.capoeiramundial.entity;
import jakarta.persistence.*; import jakarta.validation.constraints.*; import lombok.*;
@Entity @Table(name="gallery_images") @Getter @Setter @NoArgsConstructor
public class GalleryImage { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; @NotBlank private String imageUrl; private String altText; private String caption; private Integer displayOrder=0; public String getImageUrl(){return imageUrl!=null&&imageUrl.startsWith("/uploads/")?imageUrl.substring(1):imageUrl;} }
