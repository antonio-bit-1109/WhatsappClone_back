package com.example.demo.interfaces;


import java.util.List;

// i servizi implementeranno le basic crud per le crud base
public interface BasicCrud<T, K, R> {

    void create(T dataRegistration);

    void edit();

    void delete();

    R get(K dataGet);

    void getAll();
}
