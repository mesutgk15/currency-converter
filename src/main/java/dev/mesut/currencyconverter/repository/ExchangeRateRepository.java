package dev.mesut.currencyconverter.repository;

import dev.mesut.currencyconverter.model.entity.ExchangeRateEntity;
import dev.mesut.currencyconverter.model.enums.EnumsCurrency;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.function.Function;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRateEntity, Long> {

    @Query("SELECT e FROM ExchangeRateEntity e WHERE e.target = :target AND e.source = :source ORDER BY e.timeStamp DESC LIMIT 1")
    ExchangeRateEntity findBySourceAndTarget(String source, String target);


}
