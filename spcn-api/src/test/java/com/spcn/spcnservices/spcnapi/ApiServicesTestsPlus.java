package com.spcn.spcnservices.spcnapi;

import com.spcn.spcnservices.spcnapi.dtos.api.course.CourseApiDto;
import com.spcn.spcnservices.spcnapi.dtos.api.course.SaveCourseRequestDto;
import com.spcn.spcnservices.spcnapi.dtos.api.monitoring.SaveMonitoringRequestDto;
import com.spcn.spcnservices.spcnapi.dtos.api.take.TakeApiDto;
import com.spcn.spcnservices.spcnapi.dtos.api.user.AuthUserRequestDto;
import com.spcn.spcnservices.spcnapi.dtos.api.user.RegisterUserRequestDto;
import com.spcn.spcnservices.spcnapi.dtos.api.user.RegisterUserResponseDto;
import com.spcn.spcnservices.spcnapi.dtos.api.user.UserApiDto;
import com.spcn.spcnservices.spcnapi.services.api.CourseApiService;
import com.spcn.spcnservices.spcnapi.services.api.MonitoringApiService;
import com.spcn.spcnservices.spcnapi.services.api.SpcApiService;
import com.spcn.spcnservices.spcnapi.services.api.UserApiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class ApiServicesTestsPlus {

    private final UserApiService userApiService;
    private final SpcApiService spcApiService;
    private final CourseApiService courseApiService;
    private final MonitoringApiService monitoringApiService;

    @Autowired
    public ApiServicesTestsPlus(UserApiService userApiService, SpcApiService spcApiService, CourseApiService courseApiService, MonitoringApiService monitoringApiService) {
        this.userApiService = userApiService;
        this.spcApiService = spcApiService;
        this.courseApiService = courseApiService;
        this.monitoringApiService = monitoringApiService;
    }

    private Long testCaretaker = 1L;
    private Long testSpcOwner = 2L;

    private Long course1 = 1L;
    private Long course2 = 2L;


    private String testSpc1 = "A";
    private String testSpc2 = "B";


    /**
     * API-1T  Регистрация пользователя
     * */
    @Test
    void registerUser() {
        RegisterUserRequestDto user1 = new RegisterUserRequestDto();
        user1.setUsername("Евгений Шацков");
        user1.setEmail("zhenya.shatskov@yandex.ru");
        user1.setPassword("123");
        user1.setIsDependent(false);
        RegisterUserResponseDto registerUser1Response = userApiService.registerUser(user1);
        assertNotNull(registerUser1Response);
        assertNotNull(registerUser1Response.getUserId());
        testCaretaker = registerUser1Response.getUserId();
        System.out.println("testCaretaker2: " + testCaretaker);

        RegisterUserRequestDto user2 = new RegisterUserRequestDto();
        user2.setUsername("Бабушка");
        user2.setEmail("shaevvlad@mail.ru");
        user2.setPassword("321");
        user2.setIsDependent(true);
        RegisterUserResponseDto registerUser2Response = userApiService.registerUser(user2);
        assertNotNull(registerUser2Response);
        assertNotNull(registerUser2Response.getUserId());
        testSpcOwner = registerUser2Response.getUserId();
        System.out.println("testSpcOwner: " + testSpcOwner);

    }

    /**
     * API-8S  Связывание пользователей
     * */
    @Test
    void saveMonitoring(){
        monitoringApiService.saveMonitoring(new SaveMonitoringRequestDto(testCaretaker, testSpcOwner));
    }

    /**
     * API-13T Связывание дозатора и пользователя
     */
    @Test
    void updateSpcUser() {
        try {
            spcApiService.updateUser(testSpc1, testCaretaker);
            spcApiService.updateUser(testSpc2, testSpcOwner);
        }
        catch (RuntimeException e) {
            fail("Были переданы существующие serialNumber и userId, но update обновил 0 строк");
        }
    }

    /**
     * API-10S Добавление курса
     * */
    @Test
    void saveCourse() {
        CourseApiDto courseDto1 = new CourseApiDto();
        courseDto1.setMedicine("Парацетамол");
        courseDto1.setSpcSerialNumber("A");
        courseDto1.setDateStarted("2021-06-14");
        courseDto1.setDateFinished("2021-06-20");
        courseDto1.setTimetable(new ArrayList<>(Arrays.asList("12:00", "18:00")));
        courseDto1.setTakeDurationSec(3600);
        courseApiService.saveCourse(new SaveCourseRequestDto(courseDto1, testCaretaker));

        CourseApiDto courseDto2 = new CourseApiDto();
        courseDto2.setMedicine("Кардиомагнил");
        courseDto2.setSpcSerialNumber("B");
        courseDto2.setDateStarted("2021-06-07");
        courseDto2.setDateFinished("2021-07-06");
        courseDto2.setTimetable(new ArrayList<>(Arrays.asList("10:00", "15:00", "20:00")));
        courseDto2.setTakeDurationSec(3600);
        courseApiService.saveCourse(new SaveCourseRequestDto(courseDto2, testSpcOwner));
    }

    /**
     * API-12S Получение статистики по курсу
     * */
    @Test
    void getCourseStatistics(){
        List<TakeApiDto> takes1 = courseApiService.getCourseStatistics(course1);
        System.out.println("\nПриемы курса 1: " + takes1.toString() + "\n");

        List<TakeApiDto> takes2 = courseApiService.getCourseStatistics(course2);
        System.out.println("\nПриемы курса 2: " + takes2.toString() + "\n");
    }

    /**
     * API-7T  Получение пользователя
     * */
    @Test
    void getUser(){
        UserApiDto userDto1 = userApiService.getUser(testCaretaker);
        System.out.println("\nПользователь 1: " + userDto1.toString() + "\n");

        UserApiDto userDto2 = userApiService.getUser(testSpcOwner);
        System.out.println("\nПользователь 2: " + userDto2.toString() + "\n");
    }

}

