package com.viseo.c360.competence.domain.collaborator;

import com.viseo.c360.competence.domain.BaseEntity;
import com.viseo.c360.competence.domain.skill.Skill;
import com.viseo.c360.competence.dto.collaborator.CollaboratorDescription;
import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Collaborator extends BaseEntity {

    @NotNull
    @Size(min = 3, max = 20)
    @Pattern(regexp = CollaboratorDescription.Regex.PERSONNAL_ID_NUMBER)
    @Column(unique = true, nullable = false)
    String personnalIdNumber;

    @NotNull
    @Size(min = 2, max = 125)
    @Pattern(regexp = CollaboratorDescription.Regex.LAST_NAME + "*")
    String lastName;

    @NotNull
    @Size(min = 2, max = 125)
    @Pattern(regexp = CollaboratorDescription.Regex.FIRST_NAME + "*")
    String firstName;

    @NotNull
    @Email
    @Column(unique = true, nullable = false)
    String email;

    @NotNull
    String password;

    @NotNull
    Boolean isAdmin;

    String function;

    String businessUnit;

    @NotNull
    Boolean defaultPicture;

    @Valid
    @ManyToMany(mappedBy = "collaborators")
    List<Skill> links;

    public Collaborator() {
        super();
    }

    public String getPersonnalIdNumber() {
        return personnalIdNumber;
    }

    public void setPersonnalIdNumber(String personnalIdNumber) {
        this.personnalIdNumber = personnalIdNumber.trim();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.replaceAll("( )+", " ").trim();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.replaceAll("( )+", " ").trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean admin) {
        this.isAdmin = admin;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public Boolean getDefaultPicture() {
        return defaultPicture;
    }

    public void setDefaultPicture(Boolean defaultPicture) {
        this.defaultPicture = defaultPicture;
    }

    public List<Skill> getLinks() {
        return links;
    }

    public void addLink(Skill link) {
        if (this.links.indexOf(link) == -1) {
            this.links.add(link);
            link.addCollaborator(this);
        }
    }

    public void removeLink(Skill link) {
        if (this.links.indexOf(link) != -1) {
            this.links.remove(link);
            link.removeCollaborator(this);
        }
    }

    public void setLinks(List<Skill> links) {
        this.links = links;
    }
}