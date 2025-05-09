package com.example.demo.utility.mapper;

import com.example.demo.entity.Anagrafica;
import com.example.demo.entity.App_User;
import com.example.demo.interfaces.BaseEntity;
import com.example.demo.interfaces.BaseResponse;

import java.util.List;

public interface IModelMapper {
    <R extends BaseResponse> R fromEntityToDto(App_User user, Anagrafica anagrafica);
}
