package br.com.capoeiramundial.entity;
import jakarta.persistence.*; import jakarta.validation.constraints.*; import lombok.*;
@Entity @Table(name="members") @Getter @Setter @NoArgsConstructor
public class Member { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; private String name; private String nickname; @NotBlank private String graduation; private String photoUrl; @Column(length=3000) private String biography; private String role; private Integer displayOrder=0; public String getPhotoUrl(){return photoUrl!=null&&photoUrl.startsWith("/uploads/")?photoUrl.substring(1):photoUrl;} }
