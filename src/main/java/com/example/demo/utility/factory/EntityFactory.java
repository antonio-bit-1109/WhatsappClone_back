package com.example.demo.utility.factory;

import com.example.demo.dto.requests.appUser.CreateAnagraficaDTO;
import com.example.demo.dto.requests.appUser.CreateUserDTO;
import com.example.demo.entity.Anagrafica;
import com.example.demo.entity.App_User;
import com.example.demo.entity.StorageLogs;

public interface EntityFactory {
    App_User createEntityUser(CreateUserDTO data, boolean isRegisteringAdmin);

    Anagrafica createEntityAnagrafica(CreateAnagraficaDTO data);

    StorageLogs createEntityStorageLog(
            String timeStamp,
            String logType,
            Integer ProcessId,
            String message,
            String threadName
    );

}
