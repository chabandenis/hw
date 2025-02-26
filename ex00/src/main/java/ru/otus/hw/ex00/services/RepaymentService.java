package ru.otus.hw.ex00.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.ex00.dto.repayment.RepaymentCreateDto;
import ru.otus.hw.ex00.dto.repayment.RepaymentDto;
import ru.otus.hw.ex00.repositories.RepaymentRepository;

@RequiredArgsConstructor
@Service
public class RepaymentService {

    private final RepaymentRepository repaymentRepository;

    @Transactional
    public RepaymentDto create(RepaymentCreateDto userCreateDto) {
        return null;
    }

}
