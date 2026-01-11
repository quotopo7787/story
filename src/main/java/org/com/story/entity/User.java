package org.com.story.entity;

import jakarta.persistence.*;
import lombok.*;
import org.com.story.common.AuthProvider;

import java.util.Set;
@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // dùng email làm username
    @Column(nullable = false, unique = true)
    private String email;

    // null nếu login Google
    @Column
    private String password;

    private String fullName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @Enumerated(EnumType.STRING)
    private AuthProvider provider; // LOCAL, GOOGLE

    private String providerId; // google sub

    private Boolean enabled = true;
}

