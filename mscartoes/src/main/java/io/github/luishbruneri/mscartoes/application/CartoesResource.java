package io.github.luishbruneri.mscartoes.application;

import io.github.luishbruneri.mscartoes.application.representation.CartaoSaveRequest;
import io.github.luishbruneri.mscartoes.application.representation.CartoesPorClienteResponse;
import io.github.luishbruneri.mscartoes.domain.Cartao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartoes")
@Slf4j
@RequiredArgsConstructor
public class CartoesResource {

    private final CartaoService cartaoService;
    private final ClienteCartaoService clienteCartaoService;

     @GetMapping
     public String status() {
         log.info("Obtendo status do microserviço de cartões");
         return "ok";
     }

     @PostMapping
     public ResponseEntity<Cartao> cadastra(@RequestBody CartaoSaveRequest request) {
         var cartao = request.toModel();
         cartaoService.save(cartao);
         return ResponseEntity.status(HttpStatus.CREATED).build();
     }


        @GetMapping(params = "renda")
        public ResponseEntity<?> getCartoesRendaMenorIgual(@RequestParam Long renda) {
            var cartoes = cartaoService.getCartoesRendaMenorIgual(renda);
            return ResponseEntity.ok(cartoes);
        }

        @GetMapping(params = "cpf")
        public ResponseEntity<List<CartoesPorClienteResponse>> getCartoesByCliente(@RequestParam String cpf) {
            var cartoes = clienteCartaoService.listCartoesByCpf(cpf);
            return ResponseEntity.ok(cartoes.stream().map(CartoesPorClienteResponse::fromModel).toList());
        }
}
