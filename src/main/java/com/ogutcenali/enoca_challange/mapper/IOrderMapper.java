package com.ogutcenali.enoca_challange.mapper;

import com.ogutcenali.enoca_challange.dto.response.OrderResponse;
import com.ogutcenali.enoca_challange.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IOrderMapper {

    IOrderMapper INSTANCE= Mappers.getMapper(IOrderMapper.class);


    OrderResponse fromOrder(final Order order);
}
