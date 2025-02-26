package ru.otus.hw.ex00.dto.repayment;

import lombok.ToString;
import lombok.Value;

/**
 * DTO for {@link ru.otus.hw.ex00.models.Repayment}
 */
@Value
@ToString
public class RepaymentDto {
    Long id;
    String numCred;
    String status;
}