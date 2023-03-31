package com.projetddc.demo.Models.Interface;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.persistence.Column;
import jakarta.persistence.Table;


public interface Insertable{

    /**
     * Méthode pour insérer automatiquement dans la DBB un objet mappé vers une table avec hibernate
     * en utilisant l'objet et la Connection a la BDD
     * @param connection
     * @throws SQLException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public default void insert(Object object, Connection connection) throws SQLException, IllegalArgumentException, IllegalAccessException{

        Field[] fields = this.getClass().getDeclaredFields();
        
        //Préparation de la requête SQL
        String tableName = this.getClass().getAnnotation(Table.class).name();
        String sql = "INSERT INTO " + tableName;
        String s1 = "(";
        String s2 = " VALUES (";
        for (Field field : fields) if (field.isAnnotationPresent((Class<? extends Annotation>) Column.class)){

            //Récupération du nom de la collone dans la BDD
            String columnName = field.getAnnotation(Column.class).name();
            columnName = (columnName.replaceAll("([A-Z]+)([A-Z][a-z])", "$1_$2").replaceAll("([a-z])([A-Z])", "$1_$2")).toLowerCase();

            s1 += columnName+",";
            s2 += "?,";
        }
        s1 = s1.substring(0, s1.length() - 1) + ")";
        s2 = s2.substring(0, s2.length() - 1) + ");";
        sql = sql.concat(s1+s2);

        //le PreparedStatement permet d'éviter les injections SQL
        PreparedStatement p = connection.prepareStatement(sql);

        //Récuparation des paramêtres de la requête
        int i = 0;
        for (Field field : fields) if (field.isAnnotationPresent((Class<? extends Annotation>) Column.class)){
            i++;

            //Récupération de la variable dans l'objet o
            Object o = null;
            if( field.getModifiers() == 1 ){    // Vérifie que la variable est accecible
                o = field.get(object);          // Ne marche qu'avec des variables publiques
            } else{
                String fieldName = field.getName();
                fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                try {
                    Method method = object.getClass().getMethod("get" + fieldName); //Tente de trouver le getter de la variable
                    o = method.invoke(object);                                      //Appelle le getter si il existe
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e){
                    e.printStackTrace();
                }
            }

            //Insertion de la variable dans la requête
            String fieldtype = field.getType().getCanonicalName();
            switch(fieldtype){
                case "java.util.UUID":
                    p.setObject(i, o , java.sql.Types.OTHER);
                    break;
                case "java.lang.String":
                    p.setString(i, (String) o);
                    break;
                case "java.lang.Boolean":
                    p.setBoolean(i, (boolean) o);
                    break;
                case "java.util.Date":
                    p.setObject(i,o, java.sql.Types.DATE);
                    break;
                default:
                    p.setObject(i, o, java.sql.Types.OTHER);
                    break;
            }
        }
        
        // System.out.println("------SQL-VIDE------");
        // System.out.println(sql);
        // System.out.println("------SQL-PLEIN------");
        // System.out.println(p.toString());
        // System.out.println("---------------------");

        p.executeUpdate();
    }
}
