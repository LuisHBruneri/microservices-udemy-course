package io.github.luishbruneri.msavaliadorcredito.application;

import feign.FeignException;
import io.github.luishbruneri.msavaliadorcredito.application.ex.DadosClienteNotFoundException;
import io.github.luishbruneri.msavaliadorcredito.application.ex.ErroComunicacaoMicroservicesException;
import io.github.luishbruneri.msavaliadorcredito.domain.model.CartaoCliente;
import io.github.luishbruneri.msavaliadorcredito.domain.model.DadosCliente;
import io.github.luishbruneri.msavaliadorcredito.domain.model.SituacaoCliente;
import io.github.luishbruneri.msavaliadorcredito.infra.clients.CartoesResourceClient;
import io.github.luishbruneri.msavaliadorcredito.infra.clients.ClienteResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    private final ClienteResourceClient clienteResourceClient;
    private final CartoesResourceClient cartoesResourceClient;

    public SituacaoCliente obterSituacaoCliente(String cpf) {
        try {
            ResponseEntity<DadosCliente> dadosCliente = clienteResourceClient.dadosCliente(cpf);
            ResponseEntity<List<CartaoCliente>> cartoes = cartoesResourceClient.getCartoesByCliente(cpf);
            return SituacaoCliente.builder().cliente(dadosCliente.getBody()).cartoes(cartoes.getBody()).build();
        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (status == HttpStatus.NOT_FOUND.value()) {
                throw new DadosClienteNotFoundException();
            }
            throw new ErroComunicacaoMicroservicesException(e.getMessage(), status);
        }
    }
}
