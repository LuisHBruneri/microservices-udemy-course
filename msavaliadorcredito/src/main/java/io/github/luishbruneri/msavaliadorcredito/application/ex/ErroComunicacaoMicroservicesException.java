package io.github.luishbruneri.msavaliadorcredito.application.ex;

public class ErroComunicacaoMicroservicesException extends RuntimeException {

    private Integer status;
    public ErroComunicacaoMicroservicesException(String message, Integer status) {
        super(message);
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
}
