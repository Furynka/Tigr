package com.dreamteam.dto;

import java.util.Objects;

/**
 * Created by khudiakov on 21.11.2016.
*/
public class WorkerDTO {
    private Long id;
    private String email;
    private String passwordHash;
    private Boolean administrator;

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    public String getPasswordHash() {
        return this.passwordHash;
    }

    public void setAdministrator(Boolean administrator) {
        this.administrator = administrator;
    }
    public Boolean isAdministrator() {
        return this.administrator;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.email);
        hash = 67 * hash + Objects.hashCode(this.passwordHash);
        hash = 67 * hash + Objects.hashCode(this.administrator);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WorkerDTO other = (WorkerDTO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.passwordHash, other.passwordHash)) {
            return false;
        }
        if (!Objects.equals(this.administrator, other.administrator)) {
            return false;
        }
        return true;
    }

}