package com.spcn.spcnservices.spcnapi.services.api;

import com.spcn.spcnservices.spcnapi.dtos.api.course.SaveCourseRequestDto;
import com.spcn.spcnservices.spcnapi.dtos.api.take.TakeApiDto;
import com.spcn.spcnservices.spcnapi.dtos.das.CourseDasDto;
import com.spcn.spcnservices.spcnapi.dtos.das.SpcDasDto;
import com.spcn.spcnservices.spcnapi.dtos.das.TakeDasDto;
import com.spcn.spcnservices.spcnapi.dtos.das.UserDasDto;
import com.spcn.spcnservices.spcnapi.exceptions.UnexpectedException;
import com.spcn.spcnservices.spcnapi.services.das.CourseDasService;
import com.spcn.spcnservices.spcnapi.services.das.SpcDasService;
import com.spcn.spcnservices.spcnapi.services.das.TakeDasService;
import com.spcn.spcnservices.spcnapi.utils.mappers.CourseMapper;
import com.spcn.spcnservices.spcnapi.utils.mappers.TakeListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseApiService {

    private final CourseDasService courseDasService;
    private final TakeDasService takeDasService;
    private final SpcDasService spcDasService;

    @Autowired
    public CourseApiService(CourseDasService courseDasService, TakeDasService takeDasService, SpcDasService spcDasService) {
        this.courseDasService = courseDasService;
        this.takeDasService = takeDasService;
        this.spcDasService = spcDasService;
    }

    /**
     * API-10S Добавление курса
     *         saveCourse(SaveCourseRequestDto data)
     *         - data -> CourseDasDto course // CourseApiToDasMapper
     *         - Long courseId = запрос DAS-15: course
     *         - return courseId
     * */
    public Long saveCourse(SaveCourseRequestDto data){
        Long spcId = spcDasService.getIdBySerialNumber(data.getCourse().getSpcSerialNumber());
        SpcDasDto spc = new SpcDasDto(spcId);
        UserDasDto user = new UserDasDto(data.getUserId());
        CourseDasDto course = CourseMapper.mapCourseDtoToModel(data.getCourse(), user, spc);
        return courseDasService.saveCourse(course);
    }

    /**
     * API-11S Удаление курса
     *         deleteCourse(Long courseId)
     *         - Boolean success = запрос DAS-16: courseId
     *         - return success
     * */
    public void deleteCourse(Long courseId){
        boolean success = courseDasService.deleteCourse(courseId);
        if (!success) throw new UnexpectedException();
    }

    /**
     * API-12S Получение статистики по курсу
     * */
    public List<TakeApiDto> getCourseStatistics(Long courseId){
        List<TakeDasDto> takeList = takeDasService.getTakesByCourseId(courseId);
        if (takeList != null) {
            System.out.println(takeList.toString());
            return TakeListMapper.mapTakeModelToDto(takeList);
        }
        else throw new UnexpectedException();
    }

}
