package com.musicApi.infrastructure.driven_adapters.reactive_web.base;

import io.netty.channel.ChannelOption;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;
import java.util.Map;
import java.util.Optional;

public abstract class Consumer {

    @Setter
    private WebClient webClient;

    protected Consumer(String baseUrl) {
        webClient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(
                        HttpClient.create(
                                ConnectionProvider.builder("custum")
                                        .maxIdleTime(Duration.ofMillis(120))
                                        .build()
                        ).responseTimeout(Duration.ofMillis(4000))
                                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,4000)
                ))
                .baseUrl(baseUrl)
                .build();
    }

    public abstract  void configureHeaders(HttpHeaders headers);

    public <R> Mono<R> getRequest(String uri, Class<R> responseClass, Map<String, String> build) {
        return executeRequest(webClient.get().uri(buildUriWithParams(uri,build)), responseClass, Optional.empty());
    }

    public <R> Flux<R> getRequestFlux(String uri, Class<R> responseClass, Map<String, String> build) {
        return executeFluxRequest(webClient.get().uri(buildUriWithParams(uri,build)), responseClass, Optional.empty());
    }

    public <B,R> Mono<R> postRequest(String uri, B body, Class<R> responseClass) {
        return executeRequest(webClient.method(HttpMethod.POST)
                .uri(uri)
                .body(BodyInserters.fromPublisher(Mono.just(body), (Class) body.getClass())),responseClass,Optional.of(body));
    }

    public <R,B> Flux<R> executeFluxRequest (WebClient.RequestHeadersSpec requestHeadersSpec, Class<R> responseClass, Optional<B> build) {
        return requestHeadersSpec
                .headers(httpHeaders -> configureHeaders((HttpHeaders) httpHeaders))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,clientResponse -> Mono.error(new Throwable("Error al hacer la peticion en el cliente")))
                .onStatus(HttpStatusCode::is5xxServerError,clientResponse -> Mono.error(new Throwable("Error al hacer la peticion en el servidor")))
                .bodyToFlux(responseClass);
    }

    public <R,B> Mono<R> executeRequest (WebClient.RequestHeadersSpec requestHeadersSpec, Class<R> responseClass, Optional<B> build) {
        return requestHeadersSpec
                .headers(httpHeaders -> configureHeaders((HttpHeaders) httpHeaders))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,clientResponse -> Mono.error(new Throwable("Error al hacer la peticion en el cliente")))
                .onStatus(HttpStatusCode::is5xxServerError,clientResponse -> Mono.error(new Throwable("Error al hacer la peticion en el servidor")))
                .bodyToMono(responseClass);
    }

    private String buildUriWithParams(String uri, Map<String, String> build) {
        String url=UriComponentsBuilder.newInstance()
                .path(uri)
                .buildAndExpand(build)
                .toUriString();
        return url;
    }
}
