package ru.otus.hw.ex00.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.ex00.dto.repayment.RepaymentCreateDto;
import ru.otus.hw.ex00.dto.repayment.RepaymentDto;
import ru.otus.hw.ex00.mapper.RepaymentMapper;
import ru.otus.hw.ex00.models.Repayment;
import ru.otus.hw.ex00.repositories.RepaymentRepository;

@RequiredArgsConstructor
@Service
public class RepaymentService {

    private final RepaymentRepository repaymentRepository;

    private final RepaymentMapper repaymentMapper;

    private Repayment createRepayment(RepaymentCreateDto userCreateDto) {
        Repayment repayment = new Repayment();
        repayment.setNumCred(userCreateDto.getNumCred());
        repayment.setStatus("CREATED");
        return repayment;
    }

    @Transactional
    public RepaymentDto create(RepaymentCreateDto userCreateDto) {
        return repaymentMapper.toRepaymentDto(repaymentRepository.save(createRepayment(userCreateDto)));
    }

}
