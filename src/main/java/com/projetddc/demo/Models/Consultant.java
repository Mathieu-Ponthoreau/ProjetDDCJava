package com.projetddc.demo.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

import com.projetddc.demo.Models.Enum.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "consultant")
public class Consultant extends User{

    @Id
    @Column(name = "id")
    private UUID id; // = UUID.randomUUID();

    @Column(name = "defaultjob", nullable = false)
    private String defaultjob; 

    @Column(name = "available", nullable = false)
    private Boolean available;

    @Column(name = "availableat", nullable = false)
    private Date availableat;

    @Column(name = "agency_id", nullable = false)
    private UUID agency_id;

    public Consultant(){}

    public Consultant(UUID id, String userName, String firstName, String lastName, String matricule, Role role, String passwordhash, String passwordsalt, 
    String defaultjob, Boolean available, Date availableat, UUID agency_id){
            this.id = id;
            this.userName = userName;
            this.firstName = firstName;
            this.lastName =lastName;
            this.matricule = matricule;
            this.role = role;
            this.passwordhash = passwordhash;
            this.passwordsalt = passwordsalt;
            this.defaultjob = defaultjob;
            this.available = available;
            this.availableat = availableat;
            this.agency_id = agency_id;
    }

    public UUID getId() {
        return id;
    }

    public String getDefaultjob() {
        return defaultjob;
    }

    public Boolean getAvailable() {
        return available;
    }

    public Date getAvailableat() {
        return availableat;
    }

    public UUID getAgency_id() {
        return agency_id;
    }

    // @Override
    // public void insert(Connection connection) throws SQLException{
    //     String sql = "INSERT INTO users(id, username, first_name, last_name, matricule, role, passwordhash, passwordsalt)"
    //             +"VALUES (?,?,?,?,?,?,?,?);"
    //             +"INSERT INTO consultant(id, available, availableat, defaultjob, agency_id)"
    //             +"VALUES (?,?,?,?,?);";

    //     PreparedStatement p = connection.prepareStatement(sql);
        
    //     p.setObject(1, this.id, java.sql.Types.OTHER);
    //     p.setString(2, this.userName);
    //     p.setString(3, this.firstName);
    //     p.setString(4, this.lastName);
    //     p.setString(5, this.matricule);
    //     p.setInt(6, 0); // this.role
    //     p.setString(7, this.passwordhash);
    //     p.setString(8, this.passwordsalt);
    //     p.setObject(9, this.id, java.sql.Types.OTHER);
    //     p.setBoolean(10, this.available);
    //     p.setObject(11, this.availableat, java.sql.Types.DATE);
    //     p.setString(12, this.defaultjob);
    //     p.setObject(13, this.agency_id, java.sql.Types.OTHER);
        
    //     p.executeUpdate();
    // }
}
