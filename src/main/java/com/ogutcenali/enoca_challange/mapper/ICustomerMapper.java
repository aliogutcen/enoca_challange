package com.ogutcenali.enoca_challange.mapper;

import com.ogutcenali.enoca_challange.dto.response.CustomersResponse;
import com.ogutcenali.enoca_challange.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICustomerMapper {

    ICustomerMapper INSTANCE = Mappers.getMapper(ICustomerMapper.class);

    CustomersResponse fromCustomer(final Customer customer);
}
