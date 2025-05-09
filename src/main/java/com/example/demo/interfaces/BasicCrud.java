package com.example.demo.interfaces;


import java.util.List;

// i servizi implementeranno le basic crud per le crud base
public interface BasicCrud<T, K, R, S> {

    void create(T dataRegistration);

    void edit();

    S delete(K data);

    R get(K dataGet);

    List<R> getAll();
}
