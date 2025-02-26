package ru.otus.hw.ex00.dto.repayment;

import lombok.Value;

/**
 * DTO for {@link ru.otus.hw.ex00.models.Repayment}
 */
@Value
public class RepaymentCreateDto {
    String numCred;
}