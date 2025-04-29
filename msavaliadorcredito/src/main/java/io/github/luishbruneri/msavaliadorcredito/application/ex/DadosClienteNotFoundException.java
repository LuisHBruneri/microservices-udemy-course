package io.github.luishbruneri.msavaliadorcredito.application.ex;

public class DadosClienteNotFoundException extends RuntimeException {
    public DadosClienteNotFoundException() {
        super("Dados do cliente não encontrados");
    }
}
