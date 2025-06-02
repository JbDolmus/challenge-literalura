package com.alura.litera_challenge.service;

public interface IDataConverter {
    <T> T getData(String json, Class<T> clase);
}
