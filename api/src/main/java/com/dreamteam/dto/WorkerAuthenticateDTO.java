package com.dreamteam.dto;

/**
 * Created by khudiakov on 21.11.2016.
*/
public class WorkerAuthenticateDTO {
    private Long id;
    private String password;

    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }

    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
}