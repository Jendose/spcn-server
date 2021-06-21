package com.spcn.spcnservices.spcndataaccessservice.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;

@Getter
@Setter
@Entity(name = "time")
@JsonIdentityInfo(scope =  Time.class, generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Time {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(generator = "increment")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id"/*, nullable = false*/)
    private Course course;
    private LocalTime takeTime;

    public Time() {
    }

    @Override
    public String toString() {
        return takeTime.toString();
    }
}
