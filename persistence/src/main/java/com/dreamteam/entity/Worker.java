package com.dreamteam.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Entity class representing workers.
 *
 * @author Daniil Khudiakov
 */
@Entity
@Table(name="Workers")
public class Worker{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false,unique=true)
    @Pattern(regexp=".+@.+\\....?")
    @NotNull
    private String email;

    @NotNull
    private String passwordHash;

    private boolean administrator;

    public void setEmail(String email) { this.email = email; }
    public String getEmail() { return email; }

    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public String getPasswordHash() { return this.passwordHash; }

    public void setAdministrator(boolean administrator) { this.administrator = administrator; }
    public boolean isAdministrator() { return this.administrator; }

    }

    }

    }
}