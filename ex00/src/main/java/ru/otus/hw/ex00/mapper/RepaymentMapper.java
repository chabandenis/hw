package ru.otus.hw.ex00.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ru.otus.hw.ex00.dto.repayment.RepaymentDto;
import ru.otus.hw.ex00.models.Repayment;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RepaymentMapper {
    Repayment toEntity(RepaymentDto repaymentDto);

    RepaymentDto toRepaymentDto(Repayment repayment);
}