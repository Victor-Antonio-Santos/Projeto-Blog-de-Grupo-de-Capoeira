package br.com.capoeiramundial.entity;
import jakarta.persistence.*; import jakarta.validation.constraints.*; import lombok.*; import java.time.*;
@Entity @Table(name="events") @Getter @Setter @NoArgsConstructor
public class Event { public enum Status { FUTURO, REALIZADO, CANCELADO } @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; @NotBlank private String title; @Column(length=3000) private String description; @NotNull private LocalDate eventDate; private LocalTime eventTime; @NotBlank private String location; private String imageUrl; @Enumerated(EnumType.STRING) private Status status=Status.FUTURO; public String getImageUrl(){return imageUrl!=null&&imageUrl.startsWith("/uploads/")?imageUrl.substring(1):imageUrl;} }
