

package com.projetddc.demo.Models;
import java.util.UUID;

import com.projetddc.demo.Models.Enum.Role;
import com.projetddc.demo.Models.Interface.Insertable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Insertable{

    @Id
    @Column(name="id", columnDefinition = "VARCHAR(255)", insertable = false, updatable = false, nullable = false)
    protected UUID id = UUID.randomUUID();

    @Column(name = "userName", nullable = false)
    protected String userName;

    @Column(name = "firstName", nullable = false)
    protected String firstName;

    @Column(name = "lastName", nullable = false)
    protected String lastName;

    @Column(name = "matricule")
    protected String matricule;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    protected Role role;

    @Column(name = "passwordhash")
    protected String passwordhash;

    @Column(name = "passwordsalt")
    protected String passwordsalt;

    public User(){
    }
    
    public User(String userName, String firstName, String lastName, String matricule, Role role, String passwordhash, String passwordsalt){
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.matricule = matricule;
        this.role = role;
        this.passwordhash = passwordhash;
        this.passwordsalt = passwordsalt;
    }

    public UUID getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMatricule() {
        return matricule;
    }

    public Role getRole() {
        return role;
    }

    public String getPasswordhash() {
        return passwordhash;
    }

    public String getPasswordsalt() {
        return passwordsalt;
    }

    // public void insert(Connection connection) throws SQLException{
    //     String sql = "INSERT INTO users(id, username, first_name, last_name, matricule, role, passwordhash, passwordsalt)"
    //             +"VALUES (?,?,?,?,?,?,?,?)";

    //     PreparedStatement p = connection.prepareStatement(sql);
        
    //     p.setObject(1, this.id, java.sql.Types.OTHER);
    //     p.setString(2, this.userName);
    //     p.setString(3, this.firstName);
    //     p.setString(4, this.lastName);
    //     p.setString(5, this.matricule);
    //     p.setInt(6, this.role);
    //     p.setString(7, this.passwordhash);
    //     p.setString(8, this.passwordsalt);

    //     p.executeUpdate();
    // }    
}