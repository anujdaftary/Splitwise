package com.springboot.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "SPLITWISE_USER")
@Builder
public class User extends BaseModel{
    private String name;
    private String phoneNumber;
    private String email;
    @ManyToMany
    private List<Group> groups;

}
