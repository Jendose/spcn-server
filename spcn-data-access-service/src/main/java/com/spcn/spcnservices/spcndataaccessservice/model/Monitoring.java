package com.spcn.spcnservices.spcndataaccessservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "monitoring")
@JsonIdentityInfo(scope = Monitoring.class, generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Monitoring {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(generator = "increment")
    private Long id;
//    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "spc_owner_id", referencedColumnName = "id", nullable = false)
    private User spcOwner;
//    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "caretaker_id", referencedColumnName = "id", nullable = false)
    private User caretaker;
    private Boolean isHost;

    public Monitoring() {
    }

    @Override
    public String toString() {
        return "Monitoring{" +
                "spcOwner=" + spcOwner.getId() +
                ", caretaker=" + caretaker.getId() +
                ", isHost=" + isHost +
                '}';
    }
}
