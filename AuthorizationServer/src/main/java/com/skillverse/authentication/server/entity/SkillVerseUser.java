package com.skillverse.authentication.server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "skillverse_auth_users")
public class SkillVerseUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "app_user_id ", updatable = false, nullable = false)
    private UUID id;
    private String username;
    private String password;
    private String email;
    private boolean enabled=true;
    private String imageUrl;
    private Instant createdAt;
    private Instant updatedAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "app_user_id "),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<ROLE> roles=new HashSet<>();
    private PROVIDER provider=PROVIDER.LOCAL;
//    private String gender;
//    private Address address;
    @PrePersist
    protected void onCreate() {
        Instant now = Instant.now();
        if(createdAt==null){
            createdAt = now;
            updatedAt = now;
        }
    }
    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }

}
