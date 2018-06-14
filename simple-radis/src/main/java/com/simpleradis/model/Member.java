package com.simpleradis.model;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Member")
public class Member implements Serializable {

    private long id;

    private String firstName;

    private String lastName;

    public Member() {
    }

    public Member(long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}
