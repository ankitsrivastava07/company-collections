package com.company.collections.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.company.collections.dto.AddCompanyDto;
import com.company.collections.dto.CompanyDto;
import com.company.collections.entity.CompanyEntity;
import org.modelmapper.ModelMapper;

public class ObjectMapper {
    private static ModelMapper mapper = new ModelMapper();

    public static <D> D convertDtoToEntity(Object source, Class<D> target) {
        return mapper.map(source, target);
    }

    public static <D> D convertEntityToDto(Object source, Class<D> target) {
        return mapper.map(source, target);
    }
}
