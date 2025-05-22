package com.example.demo.utility.mapper;

import com.example.demo.entity.Anagrafica;
import com.example.demo.entity.App_User;
import com.example.demo.interfaces.DtoInterface;

public interface IModelMapper {
    <R extends DtoInterface> R fromEntityToDto(App_User user, Anagrafica anagrafica);
}
