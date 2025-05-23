package com.example.demo.interfaces;


import java.util.List;
import java.util.Optional;

// i servizi implementeranno le basic crud per le crud base
public interface BasicCrud<T, K, R, S, E, Y> {

    void create(T dataRegistration);

    S edit(E dataEdit);

    S delete(K data);

    R get(K dataGet);

    List<R> getAll(Optional<Y> data);
}
