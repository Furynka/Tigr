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
public class Worker{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Worker))
            return false;
        Worker other = (Worker) obj;
        if (email == null) {
            if (other.getEmail() != null)
                return false;
        } else if (!email.equals(other.getEmail()))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", passwordHash='" + passwordHash + '\'' +
                ", email='" + email + '\'' +
                ", administrator='" + administrator + '\'' +
                '}';
    }
}