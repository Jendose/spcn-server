package com.spcn.spcnservices.spcndataaccessservice.repositories;

import com.spcn.spcnservices.spcndataaccessservice.model.Monitoring;
import com.spcn.spcnservices.spcndataaccessservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitoringRepository extends JpaRepository<Monitoring, Long> {

    boolean existsBySpcOwner(User spcOwner);



}
