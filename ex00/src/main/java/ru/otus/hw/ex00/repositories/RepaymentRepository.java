package ru.otus.hw.ex00.repositories;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import ru.otus.hw.ex00.models.Repayment;

import java.util.Optional;

public interface RepaymentRepository extends JpaRepository<Repayment, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Repayment> findFirstByStatus(String status);


}