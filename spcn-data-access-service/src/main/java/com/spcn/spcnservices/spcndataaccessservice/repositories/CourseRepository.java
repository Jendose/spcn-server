package com.spcn.spcnservices.spcndataaccessservice.repositories;

import com.spcn.spcnservices.spcndataaccessservice.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    /**
     * DAS-18 Получение поля spc по полю id в таблице course, получение поля ip
     * */
    @Query("SELECT spc.ip FROM course WHERE id = :id")
    String findSpcIpByCourseId(@Param("id") Long id);

    /**
     * DAS-181 Получение поля spc по полю id в таблице course, получение поля id
     * */
    @Query("SELECT spc.id FROM course WHERE id = :id")
    Long findSpcIdByCourseId(@Param("id") Long id);

    /**
     * DAS-182 Получение поля user по полю id в таблице course, получение поля id
     * */
    @Query("SELECT user.id FROM course WHERE id = :id")
    Long findUserIdByCourseId(@Param("id") Long id);

}
