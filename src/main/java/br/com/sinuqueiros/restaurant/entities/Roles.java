package br.com.sinuqueiros.restaurant.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "roles_table")
public class Roles implements GrantedAuthority {
    @Id

    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private RoleName roleName;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Override
    public String getAuthority() {
        return this.roleName.toString();
    }
}
