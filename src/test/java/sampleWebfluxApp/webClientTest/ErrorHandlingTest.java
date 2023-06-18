package sampleWebfluxApp.webClientTest;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Mono;
import sampleWebfluxApp.controller.ReactivMathValidationController;
import sampleWebfluxApp.dto.Response;
import sampleWebfluxApp.service.ReactiveMathService;

@WebFluxTest(ReactivMathValidationController.class)
public class ErrorHandlingTest {
	

    @Autowired
    private WebTestClient client;

    @MockBean
    private ReactiveMathService reactiveMathService;

    @Test
    public void errorHandlingTest(){
        Mockito.when(reactiveMathService.findSquare(Mockito.anyInt())).thenReturn(Mono.just(new Response(1)));

        this.client
               .get()
                .uri("/reactiveMathValid/square/{number}/throw", 5) // 10-20
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.message").isEqualTo("dozwolony zakrs pomiędzy 10 - 20")
                .jsonPath("$.errorCode").isEqualTo(100)
                .jsonPath("$.input").isEqualTo(5);
    }	

}
