package br.com.capoeiramundial.entity;
import jakarta.persistence.*; import jakarta.validation.constraints.*; import lombok.*; import java.time.*;
@Entity @Table(name="training_schedules") @Getter @Setter @NoArgsConstructor
public class TrainingSchedule { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; @NotBlank private String weekday; @NotNull private LocalTime startTime; @NotNull private LocalTime endTime; @NotBlank private String venue; @NotBlank private String cityState; private String mapsUrl; private String notes; }
