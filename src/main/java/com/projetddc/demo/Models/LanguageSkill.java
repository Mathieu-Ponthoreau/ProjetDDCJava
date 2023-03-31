package com.projetddc.demo.Models;

import java.util.UUID;

import com.projetddc.demo.Models.Enum.Language;
import com.projetddc.demo.Models.Enum.Languagelevel;
import com.projetddc.demo.Models.Interface.Insertable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "languageskill")
public class LanguageSkill implements Insertable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id = UUID.randomUUID();

    @Column(name = "language", nullable = false)
    @Enumerated(EnumType.STRING)
    private Language language;

    @Column(name = "languagelevel", nullable = false)
    @Enumerated(EnumType.STRING)
    private Languagelevel languageLevel;

    @Column(name = "experience")
    private String experience;

    @Column(name = "consultant_id", nullable = false)
    private UUID consultant_id;

    public LanguageSkill(){}

    public LanguageSkill(Language language, Languagelevel languagelevel, String experience, UUID consultant_id){
        this.language = language;
        this.languageLevel = languagelevel;
        this.experience = experience;
        this.consultant_id = consultant_id;
    }

    public UUID getId() {
        return id;
    }

    public Language getLanguage() {
        return language;
    }

    public Languagelevel getLanguageLevel() {
        return languageLevel;
    }

    public String getExperience() {
        return experience;
    }

    public UUID getConsultant_id() {
        return consultant_id;
    }
}
