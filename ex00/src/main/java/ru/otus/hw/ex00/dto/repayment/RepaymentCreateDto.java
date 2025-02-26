package ru.otus.hw.ex00.dto.repayment;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

/**
 * DTO for {@link ru.otus.hw.ex00.models.Repayment}
 */
@Getter
@Setter
public class RepaymentCreateDto {
    String numCred;
}