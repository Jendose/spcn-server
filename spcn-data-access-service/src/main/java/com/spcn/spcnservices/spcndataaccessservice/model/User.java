package com.spcn.spcnservices.spcndataaccessservice.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity(name = "usr")
@JsonIdentityInfo(scope =  User.class, generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
public class User {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(generator = "increment")
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private Boolean isDependent;
//    @JsonManagedReference
//    @JsonIgnore
    @Cascade(CascadeType.SAVE_UPDATE)
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "caretaker")
    private Set<Monitoring> monitoringsByThisCaretaker = new HashSet<>();
//    @JsonManagedReference
//    @JsonIgnore
    @Cascade(CascadeType.SAVE_UPDATE)
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "spcOwner")
    private Set<Monitoring> monitoringsOfThisSpcOwner = new HashSet<>();
    @Cascade(CascadeType.SAVE_UPDATE)
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Course> courses = new HashSet<>();
    @Cascade(CascadeType.SAVE_UPDATE)
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Spc> spcSet = new HashSet<>();

    public User() {
    }

    @Override
    public String toString() {
        return "\nUser{" +
                "\nid=" + id +
                "\nname='" + name + '\'' +
                "\nemail='" + email + '\'' +
                "\npassword='" + password + '\'' +
                "\nisDependent=" + isDependent +
                "\nmonitoringsByThisCaretaker=" + monitoringsByThisCaretaker.toString() +
                "\nmonitoringsOfThisSpcOwner=" + monitoringsOfThisSpcOwner.toString() +
                "\ncourses=" + courses.toString() +
                "\nspcSet=" + spcSet.toString() +
                "\n}";
    }
}
