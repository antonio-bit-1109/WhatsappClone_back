//package com.example.demo.utility.mapper;
//
//import com.example.demo.interfaces.BaseEntity;
//import com.example.demo.interfaces.BaseResponse;
//import com.fasterxml.jackson.databind.ser.Serializers;
//import org.springframework.stereotype.Component;
//
//@Component
//public abstract class ModelMapperAbstract
//        <E extends BaseEntity>
//        implements IModelMapper<E> {
//
//    @Override
//    public <R extends BaseResponse> R fromEntityToDto(E entity, Class<R> dtoClass) {
//        if (entity == null) {
//            throw new IllegalArgumentException("Entity non può essere null.");
//        }
//        if (dtoClass == null) {
//            throw new IllegalArgumentException("La classe del DTO non può essere null.");
//        }
//
//        // Logica dinamica per mappare l'Entity nella specifica DTO
//        return modelMapper.map(entity, dtoClass);
//    }
//
//    @Override
//    public <R extends BaseResponse> E fromDtoToEntity(R dtoResponse) {
//        // Override solo se necessario nel contesto concreto
//        throw new UnsupportedOperationException("Sovrascrivere nei mapper concreti.");
//    }
//
//
//}
