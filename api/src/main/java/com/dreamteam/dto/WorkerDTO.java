package com.dreamteam.dto;

/**
 * Created by khudiakov on 21.11.2016.
*/
public class WorkerDTO {
    private Long id;
    private String email;
    private String passwordHash;
    private boolean administrator;

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

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }
    public boolean isAdministrator() {
        return this.administrator;
    }

}