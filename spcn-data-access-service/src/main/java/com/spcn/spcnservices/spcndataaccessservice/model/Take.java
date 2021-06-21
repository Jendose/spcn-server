package com.spcn.spcnservices.spcndataaccessservice.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.spcn.spcnservices.spcndataaccessservice.model.enums.TakeStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity(name = "take")
@JsonIdentityInfo(scope =  Take.class, generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Take {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(generator = "increment")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id", nullable = false)
    private Course course;
    private LocalDateTime date;
    private TakeStatus takeStatus;
    private Boolean taken;

    public Take() {
    }

    @Override
    public String toString() {
        return "Take{" +
                "date=" + date +
                ", takeStatus=" + takeStatus.name() +
                ", taken=" + taken +
                '}';
    }

}
