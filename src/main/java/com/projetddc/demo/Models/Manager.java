package com.projetddc.demo.Models;

import java.util.UUID;

import com.projetddc.demo.Models.Enum.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Manager")
public class Manager extends User{

    @Id
    @Column(name="id", columnDefinition = "VARCHAR(255)", insertable = false, updatable = false, nullable = false)
    private UUID id = UUID.randomUUID();

    @Column(name = "isDirector", nullable = false)
    private Boolean isDirector;
    

    public Manager(UUID id, Boolean isDirector) {}

    public Manager(String userName, String firstName, String lastName, String matricule, Role role, String passwordhash,
            String passwordsalt, UUID id, Boolean isDirector) {
        super(userName, firstName, lastName, matricule, role, passwordhash, passwordsalt);
        this.id = id;
        this.isDirector = isDirector;
    }

    public UUID getId() {
        return id;
    }

    public Boolean getIsDirector() {
        return isDirector;
    }
}
