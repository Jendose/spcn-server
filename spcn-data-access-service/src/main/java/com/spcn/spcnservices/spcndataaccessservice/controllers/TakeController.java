package com.spcn.spcnservices.spcndataaccessservice.controllers;

import com.spcn.spcnservices.spcndataaccessservice.dtos.SaveTakeRequestDto;
import com.spcn.spcnservices.spcndataaccessservice.dtos.TakeListDasDto;
import com.spcn.spcnservices.spcndataaccessservice.model.Course;
import com.spcn.spcnservices.spcndataaccessservice.model.Take;
import com.spcn.spcnservices.spcndataaccessservice.model.enums.TakeStatus;
import com.spcn.spcnservices.spcndataaccessservice.repositories.CourseRepository;
import com.spcn.spcnservices.spcndataaccessservice.repositories.TakeRepository;
import com.spcn.spcnservices.spcndataaccessservice.utils.mappers.TakeListMapper;
import com.spcn.spcnservices.spcndataaccessservice.utils.mappers.TakeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
public class TakeController {

    private final TakeRepository takeRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public TakeController(TakeRepository takeRepository, CourseRepository courseRepository) {
        this.takeRepository = takeRepository;
        this.courseRepository = courseRepository;
    }

    /**
     * DAS-20 Добавление записи в таблицу take
     * */
    @PostMapping("/takes")
    public ResponseEntity<Long> saveTake(@RequestBody SaveTakeRequestDto data){
        Course courseRef = courseRepository.getById(data.getCourseId());
        Take take = TakeMapper.mapTakeDtoToModel(data.getTake());
        take.setCourse(courseRef);
        return new ResponseEntity<>(takeRepository.saveAndFlush(take).getId(), HttpStatus.OK);
    }

    /**
     * DAS-21 Поиск записей по полю course в таблице take
     * */
    @GetMapping("courses/{courseId}/takes")
    public ResponseEntity<TakeListDasDto> getTakesByCourseId(@PathVariable Long courseId){
        Course courseRef = courseRepository.getById(courseId);
        List<Take> takeList = takeRepository.findTakesByCourse(courseRef).orElse(null);
        return new ResponseEntity<>(TakeListMapper.mapTakeListModelToDto(takeList), HttpStatus.OK);
    }

    /**
     * DAS-22C Обновление поля taken по полю id в таблице take
     * */
    @PutMapping("/takes/{takeId}/taken")
    public ResponseEntity<Boolean> updateTakenById(@PathVariable Long takeId, @RequestBody Boolean taken){
        int responseInt = takeRepository.updateTakenById(taken, takeId);
        return new ResponseEntity<>(responseInt != 0, HttpStatus.OK);
    }

    /**
     * DAS-23C Обновление поля takeStatus по полю id в таблице take
     * */
    @PutMapping("/takes/{takeId}/takeStatus")
    public ResponseEntity<Boolean> updateTakeStatusById(@PathVariable Long takeId, @RequestBody TakeStatus takeStatus){
        int responseInt = takeRepository.updateTakeStatusById(takeStatus, takeId);
        return new ResponseEntity<>(responseInt != 0, HttpStatus.OK);
    }

}
