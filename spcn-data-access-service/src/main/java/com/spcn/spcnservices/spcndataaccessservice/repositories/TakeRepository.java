package com.spcn.spcnservices.spcndataaccessservice.repositories;

import com.spcn.spcnservices.spcndataaccessservice.model.Course;
import com.spcn.spcnservices.spcndataaccessservice.model.Take;
import com.spcn.spcnservices.spcndataaccessservice.model.enums.TakeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TakeRepository extends JpaRepository<Take, Long> {

    Optional<List<Take>> findTakesByCourse(Course course);

    /**
     * DAS-22C Обновление поля taken по полю id в таблице take
     * */
    @Modifying
    @Query("UPDATE take set taken = :taken where id = :id")
    int updateTakenById(@Param("taken") Boolean taken, @Param("id") Long id);

    /**
     * DAS-23C Обновление поля takeStatus по полю id в таблице take
     * */
    @Modifying
    @Query("UPDATE take set takeStatus = :takeStatus where id = :id")
    int updateTakeStatusById(@Param("takeStatus") TakeStatus takeStatus, @Param("id") Long id);

}
