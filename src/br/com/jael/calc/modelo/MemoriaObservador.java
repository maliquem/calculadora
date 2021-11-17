package br.com.jael.calc.modelo;

@FunctionalInterface
public interface MemoriaObservador {

    void valorAlterado(String novoValor);

}
