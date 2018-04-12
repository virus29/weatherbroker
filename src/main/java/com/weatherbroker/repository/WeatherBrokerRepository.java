package com.weatherbroker.repository;

import com.weatherbroker.entity.WeatherBroker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface WeatherBrokerRepository extends JpaRepository<WeatherBroker, Long>, JpaSpecificationExecutor<WeatherBroker> {
}
