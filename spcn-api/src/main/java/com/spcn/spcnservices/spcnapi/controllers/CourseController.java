package com.spcn.spcnservices.spcnapi.controllers;

import com.spcn.spcnservices.spcnapi.dtos.api.course.SaveCourseRequestDto;
import com.spcn.spcnservices.spcnapi.dtos.api.take.TakeApiDto;
import com.spcn.spcnservices.spcnapi.services.api.CourseApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CourseController {

    private final CourseApiService courseApiService;

    @Autowired
    public CourseController(CourseApiService courseApiService) {
        this.courseApiService = courseApiService;
    }

    /**
     * API-10C  Добавление курса -
     *          добавление записи в таблицу course
     * */
    @PostMapping("/courses")
    public ResponseEntity<Long> saveCourse(@RequestBody SaveCourseRequestDto data){
        Long response = courseApiService.saveCourse(data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * API-11C  Удаление курса -
     *          удаление записи из таблицы course
     * */
    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long courseId){
        courseApiService.deleteCourse(courseId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // TODO: Будет вызываться реактовским визуализатором для отображения списка приемов курса
    /**
     * API-C12  Получение статистики по курсу -
     *          поиск записей по полю course в таблице take,
     *          сборка списка тейков
     * */
    @GetMapping("/courses/{courseId}/statistics")
    public ResponseEntity<List<TakeApiDto>> getCourseStatistics(@PathVariable Long courseId){
        return new ResponseEntity<>(courseApiService.getCourseStatistics(courseId), HttpStatus.OK);
    }

}
