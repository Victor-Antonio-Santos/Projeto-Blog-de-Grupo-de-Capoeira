package br.com.capoeiramundial.entity;
import jakarta.persistence.*; import jakarta.validation.constraints.*; import lombok.*;
@Entity @Table(name="contact_info") @Getter @Setter @NoArgsConstructor
public class ContactInfo { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; @NotBlank private String type; @NotBlank private String label; @NotBlank @Column(name="contact_value") private String value; private String url; private boolean visible=true; }
