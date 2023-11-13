package dev.mesut.currencyconverter.repository;

import dev.mesut.currencyconverter.model.entity.ConversionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConversionRepository extends JpaRepository<ConversionEntity, Long> {

    ConversionEntity findByTransactionId(String transactionId);
    List<ConversionEntity> findAllByTimeStampBetween(LocalDateTime start, LocalDateTime end);
}
