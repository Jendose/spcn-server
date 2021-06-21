package com.spcn.spcnservices.spcndataaccessservice.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
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
@Entity(name = "spc")
@JsonIdentityInfo(scope =  Spc.class, generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Spc {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(generator = "increment")
    private Long id;
    @Column(unique = true, nullable = false)
    private String serialNumber;
    @Column(unique = true)
    private String ip;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @Cascade(CascadeType.SAVE_UPDATE)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "spc")
    private Set<Course> courses = new HashSet<>();

    public Spc() {
    }

    @Override
    public String toString() {
        return serialNumber;
    }
}
