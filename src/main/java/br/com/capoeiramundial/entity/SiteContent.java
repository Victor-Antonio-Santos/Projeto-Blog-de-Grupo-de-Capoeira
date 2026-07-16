package br.com.capoeiramundial.entity;
import jakarta.persistence.*; import jakarta.validation.constraints.*; import lombok.*;
@Entity @Table(name="site_contents") @Getter @Setter @NoArgsConstructor
public class SiteContent { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; @NotBlank @Column(unique=true) private String contentKey; @Column(length=10000) private String contentValue; private String label; public SiteContent(String k,String v,String l){contentKey=k;contentValue=v;label=l;} }
