package com.spcn.spcnservices.spcndataaccessservice;

import com.spcn.spcnservices.spcndataaccessservice.model.Spc;
import com.spcn.spcnservices.spcndataaccessservice.model.Take;
import com.spcn.spcnservices.spcndataaccessservice.model.enums.TakeStatus;
import com.spcn.spcnservices.spcndataaccessservice.repositories.CourseRepository;
import com.spcn.spcnservices.spcndataaccessservice.repositories.SpcRepository;
import com.spcn.spcnservices.spcndataaccessservice.repositories.TakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootApplication
@Transactional
public class SpcnDataAccessServiceApplication implements CommandLineRunner{

    SpcRepository spcRepository;
    CourseRepository courseRepository;
    TakeRepository takeRepository;

    @Autowired
    public SpcnDataAccessServiceApplication(SpcRepository spcRepository, CourseRepository courseRepository, TakeRepository takeRepository) {
        this.spcRepository = spcRepository;
        this.courseRepository = courseRepository;
        this.takeRepository = takeRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpcnDataAccessServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        // ДЛЯ ВИЗУАЛИЗАТОРА
//
//        for (int i=0; i<35; i++){
//            Spc spc = new Spc();
//            spc.setIp("Test1_" + i);
//            spc.setSerialNumber("Test1_" + i);
//            spcRepository.save(spc);
//        }
//
//        Spc spc1 = new Spc();
//        spc1.setIp("http://localhost:8084");
//        spc1.setSerialNumber("A");
//
//        spcRepository.save(spc1);
//
//        for (int i=0; i<70; i++){
//            Spc spc = new Spc();
//            spc.setIp("Test2_" + i);
//            spc.setSerialNumber("Test2_" + i);
//            spcRepository.save(spc);
//        }
//
//        Spc spc2 = new Spc();
//        spc2.setIp("http://localhost:8085");
//        spc2.setSerialNumber("B");
//
//        spcRepository.save(spc2);

        // Для тестов для Тани

//        Spc spc1 = new Spc();
//        spc1.setIp("http://localhost:8084");
//        spc1.setSerialNumber("A");
//
//        spcRepository.save(spc1);
//
//        Spc spc2 = new Spc();
//        spc2.setIp("http://localhost:8085");
//        spc2.setSerialNumber("B");
//
//        spcRepository.save(spc2);
//
//        Spc spc3 = new Spc();
//        spc3.setIp("http://localhost:8086");
//        spc3.setSerialNumber("C");
//
//        spcRepository.save(spc3);

//        Take take1 = new Take();
//        take1.setCourse(courseRepository.getById(1L));
//        take1.setDate(LocalDateTime.now());
//        take1.setTakeStatus(TakeStatus.OK);
//        take1.setTaken(true);
//
//        takeRepository.save(take1);
//
//        Take take2 = new Take();
//        take2.setCourse(courseRepository.getById(1L));
//        take2.setDate(LocalDateTime.now());
//        take2.setTakeStatus(TakeStatus.OK);
//        take2.setTaken(false);
//
//        takeRepository.save(take2);
//
//        Take take3 = new Take();
//        take3.setCourse(courseRepository.getById(1L));
//        take3.setDate(LocalDateTime.now());
//        take3.setTakeStatus(TakeStatus.LOST);
//
//        takeRepository.save(take3);
//
//        Take take4 = new Take();
//        take4.setCourse(courseRepository.getById(1L));
//        take4.setDate(LocalDateTime.now());
//        take4.setTakeStatus(TakeStatus.WAIT);
//
//        takeRepository.save(take4);

    }
}
