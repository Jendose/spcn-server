package com.spcn.spcnservices.spcndataaccessservice.controllers;

import com.spcn.spcnservices.spcndataaccessservice.model.Course;
import com.spcn.spcnservices.spcndataaccessservice.model.Time;
import com.spcn.spcnservices.spcndataaccessservice.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Transactional
public class CourseController {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    /**
     * DAS-15 Добавление записи в таблицу course
     * */
    @PostMapping("/courses")
    public ResponseEntity<Long> saveCourse(@RequestBody Course course){
        for(Time t : course.getTimetable()){
            t.setCourse(course);
        }
        return new ResponseEntity<>(courseRepository.saveAndFlush(course).getId(), HttpStatus.OK);
    }

    /**
     * DAS-16 Удаление записи из таблицы course
     * */
    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<Boolean> deleteCourse(@PathVariable Long courseId){
        boolean valid = courseRepository.existsById(courseId);
        if (valid) {
            courseRepository.deleteById(courseId);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        else return new ResponseEntity<>(false, HttpStatus.OK);
    }

    /**
     * DAS-18 Получение поля spc по полю id в таблице course, получение поля ip
     * */
    @GetMapping("/courses/{courseId}/spc/ip")
    public ResponseEntity<String> getSpcIpByCourseId(@PathVariable Long courseId){
        return new ResponseEntity<>(courseRepository.findSpcIpByCourseId(courseId), HttpStatus.OK);
    }

    // TODO: Будет вызываться реактовским визуализатором для получения id дозатора, назначенного на данный курс
    /**
     * DAS-181 Получение поля spc по полю id в таблице course, получение поля id
     * */
    @GetMapping("/courses/{courseId}/spc/id")
    public ResponseEntity<Long> getSpcIdByCourseId(@PathVariable Long courseId){
        return new ResponseEntity<>(courseRepository.findSpcIdByCourseId(courseId), HttpStatus.OK);
    }

    // TODO: Будет вызываться реактовским визуализатором для получения id пользователя данного курс
    /**
     * DAS-182 Получение поля user по полю id в таблице course, получение поля id
     * */
    @GetMapping("/courses/{courseId}/user/id")
    public ResponseEntity<Long> getUserIdByCourseId(@PathVariable Long courseId){
        return new ResponseEntity<>(courseRepository.findUserIdByCourseId(courseId), HttpStatus.OK);
    }

}
