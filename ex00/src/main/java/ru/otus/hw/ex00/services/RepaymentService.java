package ru.otus.hw.ex00.services;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.ex00.dto.repayment.RepaymentCreateDto;
import ru.otus.hw.ex00.dto.repayment.RepaymentDto;
import ru.otus.hw.ex00.mapper.RepaymentMapper;
import ru.otus.hw.ex00.models.Repayment;
import ru.otus.hw.ex00.repositories.RepaymentRepository;

import java.util.Optional;

@Log4j2
@RequiredArgsConstructor
@Service
public class RepaymentService {

    private final RepaymentRepository repaymentRepository;

    private final RepaymentMapper repaymentMapper;

    private final ObjectMapper objectMapper;

    private Repayment createRepayment(RepaymentCreateDto userCreateDto) {
        ObjectMapper objectMapper = new ObjectMapper();

        Repayment repayment = new Repayment();
        repayment.setNumCred(userCreateDto.getNumCred());
        repayment.setStatus("CREATED");

        try {
            repayment.setJsonData(userCreateDto);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return repayment;
    }

    @Transactional
    public RepaymentDto create(RepaymentCreateDto userCreateDto) {
        return repaymentMapper.toRepaymentDto(repaymentRepository.save(createRepayment(userCreateDto)));
    }

    @Transactional
    public Optional<Repayment> findFirstByStatus() {
        var repayment = repaymentRepository.findFirstByStatus("CREATED");
        if (repayment.isPresent()) {
            repayment.get().setStatus("PROCESS");

            if (repayment.get().getJsonData() != null) {
                log.debug("json ");
                try {
                    var ss = repayment.get().getJsonData();

                    var rr = objectMapper.writeValueAsString(ss);
                    log.debug("rr " + rr);

                    var yy = objectMapper.readValue(rr, RepaymentCreateDto.class);
                    log.debug("yy " + yy.getNumCred());

                } catch (Exception e) {
                    log.debug("error " + e.getMessage());
                }
            }

        }


/*        log.debug("Начала сна");
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.debug("Закончили спать");*/

        return repayment;
    }

}
