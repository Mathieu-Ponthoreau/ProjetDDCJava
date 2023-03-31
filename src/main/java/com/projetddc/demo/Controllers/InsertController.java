package com.projetddc.demo.Controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import com.projetddc.demo.Models.Consultant;
import com.projetddc.demo.Models.LanguageSkill;
import com.projetddc.demo.Models.Manager;
import com.projetddc.demo.Models.User;
import com.projetddc.demo.Models.Enum.Language;
import com.projetddc.demo.Models.Enum.Languagelevel;
import com.projetddc.demo.Models.Enum.Role;
import com.projetddc.demo.Models.Interface.Insertable;

@RestController
public class InsertController {
    Connection connection;
    
    @Value( "${spring.datasource.url}" )
    private String bddUrl;

    @Value( "${spring.datasource.username}" )
    private String user;

    @Value( "${spring.datasource.password}" )
    private String password;

    public InsertController(){}

    Logger logger = LoggerFactory.getLogger(InsertController.class);

    @Bean
    public void testBDD() throws SQLException, IllegalArgumentException, IllegalAccessException{
        connection = DriverManager.getConnection(bddUrl, user, password);

        User user = new User("testuser","test","ing","test", Role.Consultant,"pass","word");
    
        Manager manager = new Manager("testuser","test","ing","test", Role.Consultant,"pass","word", user.getId(),true);
        Consultant consultant = new Consultant(user.getId(),"Jhony","John","Doe","test",Role.Consultant,"pass","word","test",true, new Date(), UUID.randomUUID());
        LanguageSkill languageSkill = new LanguageSkill(Language.ALLEMAND, Languagelevel.Notions, "xp",UUID.randomUUID());

        // InsertObject(languageSkill, connection);
        // InsertObject(user, connection);
        // InsertObject(consultant, connection);
        // InsertObject(manager, connection);
    }

    // Permet d'insérer n'importe quel objet utilisant l'interface Insertable dans la BDD
    private void InsertObject(Insertable object, Connection connection) throws SQLException, IllegalArgumentException, IllegalAccessException{
        object.insert(object, connection);
    }

}
