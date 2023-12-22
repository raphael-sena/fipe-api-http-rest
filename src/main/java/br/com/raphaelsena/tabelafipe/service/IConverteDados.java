package br.com.raphaelsena.tabelafipe.service;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
