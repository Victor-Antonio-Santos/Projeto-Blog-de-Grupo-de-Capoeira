package br.com.capoeiramundial.entity;
import jakarta.persistence.*; import lombok.*;
@Entity @Table(name="admin_users") @Getter @Setter @NoArgsConstructor
public class AdminUser { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; @Column(nullable=false,unique=true) private String username; @Column(nullable=false) private String password; @Column(nullable=false) private boolean enabled=true; public AdminUser(String u,String p){username=u;password=p;} }
