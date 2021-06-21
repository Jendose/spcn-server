package com.spcn.spcnservices.spcndataaccessservice.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity(name = "course")
@JsonIdentityInfo(scope = Course.class, generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Course {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(generator = "increment")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "spc_id", referencedColumnName = "id", nullable = false)
    private Spc spc;
    private String medicine;
    @Cascade(CascadeType.PERSIST)
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "course")
    private Set<Time> timetable = new HashSet<>();
    @Cascade(CascadeType.SAVE_UPDATE)
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "course")
    private Set<Take> takeSet = new HashSet<>();
    private LocalDate dateStarted;
    private LocalDate dateFinished;
    private Integer takeDurationSec;

    public Course() {
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", spc=" + spc.toString() +
                ", medicine='" + medicine + '\'' +
                ", timetable=" + timetable.toString() +
                ", takeSet=" + takeSet.toString() +
                ", dateStarted=" + dateStarted +
                ", dateFinished=" + dateFinished +
                ", takeDurationSec=" + takeDurationSec +
                '}';
    }
}
