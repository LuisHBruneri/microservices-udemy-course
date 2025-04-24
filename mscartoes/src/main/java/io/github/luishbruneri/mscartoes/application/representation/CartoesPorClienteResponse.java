package io.github.luishbruneri.mscartoes.application.representation;

import io.github.luishbruneri.mscartoes.domain.Cartao;
import io.github.luishbruneri.mscartoes.domain.ClienteCartao;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartoesPorClienteResponse {
    private String nome;
    private String bandeira;
    private BigDecimal limiteLiberado;

    public static CartoesPorClienteResponse fromModel(ClienteCartao model) {
        var response = new CartoesPorClienteResponse();
        response.setNome(model.getCartao().getNome());
        response.setBandeira(model.getCartao().getBandeira().toString());
        response.setLimiteLiberado(model.getCartao().getLimiteBasico());
        return response;
    }
}
