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
class ApiServicesTests {

    private final UserApiService userApiService;
    private final SpcApiService spcApiService;
    private final CourseApiService courseApiService;
    private final MonitoringApiService monitoringApiService;

    @Autowired
    public ApiServicesTests(UserApiService userApiService, SpcApiService spcApiService, CourseApiService courseApiService, MonitoringApiService monitoringApiService) {
        this.userApiService = userApiService;
        this.spcApiService = spcApiService;
        this.courseApiService = courseApiService;
        this.monitoringApiService = monitoringApiService;
    }

    private Long testCaretaker1 = 1L;
    private Long testCaretaker2 = 2L;
    private Long testSpcOwner = 3L;

    private Long course1 = 1L;
    private Long course2 = 2L;
    private Long course3 = 3L;


    private String testSpc1 = "A";
    private String testSpc2 = "B";
    private String testSpc3 = "C";


    /**
     * API-1T  Регистрация пользователя
     * */
    @Test
    void registerUser() {
        RegisterUserRequestDto user1 = new RegisterUserRequestDto();
        user1.setUsername("Татьяна Головко");
        user1.setEmail("amacatoe@icloud.com");
        user1.setPassword("321");
        user1.setIsDependent(false);
        RegisterUserResponseDto registerUser1Response = userApiService.registerUser(user1);
        assertNotNull(registerUser1Response);
        assertNotNull(registerUser1Response.getUserId());
        testCaretaker1 = registerUser1Response.getUserId();
        System.out.println("testCaretaker1: " + testCaretaker1);

        RegisterUserRequestDto user2 = new RegisterUserRequestDto();
        user2.setUsername("Евгений Шацков");
        user2.setEmail("zhenya.shatskov@yandex.ru");
        user2.setPassword("123");
        user2.setIsDependent(false);
        RegisterUserResponseDto registerUser2Response = userApiService.registerUser(user2);
        assertNotNull(registerUser2Response);
        assertNotNull(registerUser2Response.getUserId());
        testCaretaker2 = registerUser2Response.getUserId();
        System.out.println("testCaretaker2: " + testCaretaker2);

        RegisterUserRequestDto user3 = new RegisterUserRequestDto();
        user3.setUsername("Babushka");
        user3.setEmail("shaevvlad@mail.ru");
        user3.setPassword("321");
        user3.setIsDependent(true);
        RegisterUserResponseDto registerUser3Response = userApiService.registerUser(user3);
        assertNotNull(registerUser3Response);
        assertNotNull(registerUser3Response.getUserId());
        testSpcOwner = registerUser3Response.getUserId();
        System.out.println("testSpcOwner: " + testSpcOwner);

    }

    /**
     * API-5T  Изменение имени пользователя
     * */
    @Test
    void updateUserName() {
        try {
            userApiService.updateName(testSpcOwner,"Бабушка");

        }
        catch (RuntimeException e) {
            fail("Был передан существующий userId, но update обновил 0 строк");
        }
    }

    /**
     * API-8S  Связывание пользователей
     * */
    @Test
    void saveMonitoring(){
        monitoringApiService.saveMonitoring(new SaveMonitoringRequestDto(testCaretaker2, testSpcOwner));
        monitoringApiService.saveMonitoring(new SaveMonitoringRequestDto(testCaretaker1, testSpcOwner));
    }

    /**
     * API-2T  Аавторизация пользователя
     * */
    @Test
    void authUser() {

        AuthUserRequestDto authUserRequestDto = new AuthUserRequestDto();
        authUserRequestDto.setEmail("zhenya.shatskov@yandex.ru");

        try {
            authUserRequestDto.setPassword("123");
            UserApiDto userDto1 = userApiService.authUser(authUserRequestDto);
            assertNotNull(userDto1);
            System.out.println(userDto1);
        }
        catch (RuntimeException e) {
            fail("Был передан существующий email: zhenya.shatskov@yandex.ru, но что-то пошло не так");
        }

        boolean noException1 = true;
        try {
            authUserRequestDto.setPassword("incorrect");
            UserApiDto userDto2 = userApiService.authUser(authUserRequestDto);
            assertNotNull(userDto2);
            System.out.println(userDto2);
        }
        catch (RuntimeException e) {
            System.out.println("Успешная обработка исключения:");
            System.out.println(e.getMessage());
            noException1 = false;
        }
        assertFalse(noException1, "Не выпала ошибка при передаче неверного пароля");

        boolean noException2 = true;
        try {
            authUserRequestDto.setEmail("incorrect");
            authUserRequestDto.setPassword("123");
            UserApiDto userDto3 = userApiService.authUser(authUserRequestDto);
            assertNotNull(userDto3);
            System.out.println(userDto3);
        }
        catch (RuntimeException e) {
            System.out.println("Успешная обработка исключения:");
            System.out.println(e.getMessage());
            noException2 = false;
        }
        assertFalse(noException2, "Не выпала ошибка при передаче несуществующего email");

    }

    /**
     * API-13T Связывание дозатора и пользователя
     */
    @Test
    void updateSpcUser() {
        try {
            spcApiService.updateUser(testSpc2, testCaretaker2);
            spcApiService.updateUser(testSpc3, testSpcOwner);
        }
        catch (RuntimeException e) {
            fail("Были переданы существующие serialNumber и userId, но update обновил 0 строк");
        }
    }

    /**
     * API-15T Получение данных о существовании связи дозатора и пользователя
     * */
    @Test
    void isScpOwned(){
        try {
            boolean isSpc1Owned = spcApiService.isScpOwned(testSpc1);
            boolean isSpc2Owned = spcApiService.isScpOwned(testSpc2);
            System.out.println("У spc " + testSpc1 + " есть юзер: " + isSpc1Owned);
            System.out.println("У spc " + testSpc2 + " есть юзер: " + isSpc2Owned);
        }
        catch (RuntimeException e) {
            fail("Был передан существующий serialNumber, но дозатор не был найден");
        }

        boolean noException = true;
        try {
            spcApiService.isScpOwned("incorrect");
        }
        catch (RuntimeException e) {
            System.out.println("Успешная обработка исключения:");
            System.out.println(e.getMessage());
            noException = false;
        }
        assertFalse(noException, "Не выпала ошибка при передаче несуществующего serialNumber");

    }

    /**
     * API-10S Добавление курса
     * */
    @Test
    void saveCourse() {
        CourseApiDto courseDto1 = new CourseApiDto();
        courseDto1.setMedicine("Парацитамол (тест завершенного)");
        courseDto1.setSpcSerialNumber(testSpc2);
        courseDto1.setDateStarted("2021-05-25");
        courseDto1.setDateFinished("2021-05-31");
        courseDto1.setTimetable(new ArrayList<>(Arrays.asList("10:00", "15:00", "20:00")));
        courseDto1.setTakeDurationSec(1800);
        courseApiService.saveCourse(new SaveCourseRequestDto(courseDto1, testCaretaker2));

        CourseApiDto courseDto2 = new CourseApiDto();
        courseDto2.setMedicine("Арбидол (тест текущего)");
        courseDto2.setSpcSerialNumber(testSpc2);
        courseDto2.setDateStarted("2021-06-06");
        courseDto2.setDateFinished("2021-06-20");
        courseDto2.setTimetable(new ArrayList<>(Arrays.asList("10:00", "20:00")));
        courseDto2.setTakeDurationSec(3600);
        courseApiService.saveCourse(new SaveCourseRequestDto(courseDto2, testCaretaker2));

        CourseApiDto courseDto3 = new CourseApiDto();
        courseDto3.setMedicine("Кардиомагнил (тест предстоящего)");
        courseDto3.setSpcSerialNumber(testSpc3);
        courseDto3.setDateStarted("2021-06-20");
        courseDto3.setDateFinished("2021-07-20");
        courseDto3.setTimetable(new ArrayList<>(Arrays.asList("10:00", "13:00", "16:00", "19:00", "22:00")));
        courseDto3.setTakeDurationSec(1200);
        courseApiService.saveCourse(new SaveCourseRequestDto(courseDto3, testSpcOwner));
    }

    /**
     * API-12S Получение статистики по курсу
     * */
    @Test
    void getCourseStatistics(){
        List<TakeApiDto> takes = courseApiService.getCourseStatistics(course1);
        System.out.println(takes.toString());
    }

    /**
     * API-7T  Получение пользователя
     * */
    @Test
    void getUser(){
        UserApiDto userDto = userApiService.getUser(testCaretaker2);
        assertNotNull(userDto);
        assertNotNull(userDto.getCourses());
        assertNotNull(userDto.getSpcOwners());
        assertNotNull(userDto.getSpcSerialNumberList());
        System.out.println(userDto.toString());
    }

    /**
     * API-14T Разрыв связи дозатора и пользователя
     */
    @Test
    void cleanSpcUser() {
        try {
            spcApiService.cleanUser(testSpc2);
        }
        catch (RuntimeException e) {
            fail("Был передан существующий serialNumber, но update обновил 0 строк");
        }
    }

    /**
     * API-6T  Изменение зависимости пользователя
     */
    @Test
    void updateDependency(){
        userApiService.updateDependency(testSpcOwner, false);
    }

}
