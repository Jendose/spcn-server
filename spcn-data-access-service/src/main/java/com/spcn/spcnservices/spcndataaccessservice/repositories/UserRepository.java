package com.spcn.spcnservices.spcndataaccessservice.repositories;

import com.spcn.spcnservices.spcndataaccessservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * DAS-2R Проверка наличия записи по полю email в таблице usr
     * */
    boolean existsByEmail(String email);

    /**
     * DAS-3R Поиск записи по полю email в таблице usr
     * */
    Optional<User> findByEmail(String email);

    /**
     * DAS-5R Получение поля id по полю email в таблице usr
     * */
    @Query("SELECT id FROM usr WHERE email = :email")
    Optional<Long> findIdByEmail(@Param("email") String email);

    /**
     * DAS-55R Получение поля name по полю email в таблице usr
     * */
    @Query("SELECT name FROM usr WHERE email = :email")
    Optional<String> findNameByEmail(@Param("email") String email);

    /**
     * DAS-7R Получение поля password по полю id в таблице usr
     * */
    @Query("SELECT password FROM usr WHERE id = :id")
    Optional<String> findPasswordById(@Param("id") Long id);

    /**
     * DAS-8R Получение поля isDependent по полю id в таблице usr
     * */
    @Query("SELECT isDependent FROM usr WHERE id = :id")
    Optional<Boolean> findIsDependentById(@Param("id") Long id);

    /**
     * DAS-9R Обновление поля isDependent по полю id в таблице usr
     * */
    @Modifying
    @Query("UPDATE usr set isDependent = :isDependent where id = :id")
    int updateIsDependentById(@Param("isDependent") Boolean isDependent, @Param("id") Long id);

    /**
     * DAS-10R Обновление поля password по полю email в таблице usr
     * */
    @Modifying
    @Query("UPDATE usr set password = :password where email = :email")
    int updatePasswordByEmail(@Param("password") String password, @Param("email") String email);

    /**
     * DAS-11R Обновление поля name по полю id в таблице usr
     * */
    @Modifying
    @Query("UPDATE usr set name = :name where id = :id")
    int updateNameById(@Param("name") String name, @Param("id") Long id);

}
