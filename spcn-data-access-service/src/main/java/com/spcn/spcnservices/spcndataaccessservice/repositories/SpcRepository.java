package com.spcn.spcnservices.spcndataaccessservice.repositories;

import com.spcn.spcnservices.spcndataaccessservice.model.Spc;
import com.spcn.spcnservices.spcndataaccessservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpcRepository extends JpaRepository<Spc, Long> {

    boolean existsBySerialNumber(String serialNumber);

    Spc findBySerialNumber(String serialNumber);

    /**
     * DAS-28R Обновление поля user по полю serialNumber в таблице spc
     * */
    @Modifying
    @Query("UPDATE spc set user = :user where serial_number = :serialNumber")
    int updateUserBySerialNumber(@Param("user") User user, @Param("serialNumber") String serialNumber);

    /**
     * DAS-29R Очистка поля user по полю serialNumber в таблице spc
     * */
    @Modifying
    @Query("UPDATE spc set user = null where serial_number = :serialNumber")
    int cleanUserBySerialNumber(@Param("serialNumber") String serialNumber);

    /**
     * DAS-26.1R Получение поля user по полю serialNumber в таблице spc
     */
    @Query("SELECT user FROM spc WHERE serial_number = :serialNumber")
    Optional<User> findUserBySerialNumber(@Param("serialNumber") String serialNumber);

    /**
     * DAS-262R Получение поля id по полю serialNumber в таблице spc
     */
    @Query("SELECT id FROM spc WHERE serial_number = :serialNumber")
    Optional<Long> findIdBySerialNumber(@Param("serialNumber") String serialNumber);

    /**
     * DAS-262R Получение поля ip по полю serialNumber в таблице spc
     */
    @Query("SELECT ip FROM spc WHERE serial_number = :serialNumber")
    Optional<String> findIpBySerialNumber(@Param("serialNumber") String serialNumber);


}
