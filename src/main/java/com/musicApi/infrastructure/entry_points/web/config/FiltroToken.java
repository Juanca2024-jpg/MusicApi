package com.musicApi.infrastructure.entry_points.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musicApi.infrastructure.helpers.common.ResponseDTO;
import com.musicApi.infrastructure.helpers.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FiltroToken implements WebFilter {

    private final JWTUtils jwtUtils;

    private ServerWebExchange exchange;

    private WebFilterChain chain;

    private String requestURI;

    private String token;

    private String mensajeError;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        this.exchange = exchange;

        this.chain = chain;

        this.requestURI = exchange.getRequest().getURI().getPath();

        this.token = getToken(exchange.getRequest());

        agregarCabecerasCORS();

        if (HttpMethod.OPTIONS.equals(exchange.getRequest().getMethod())) {
            exchange.getResponse().setStatusCode(HttpStatus.OK);
            return Mono.empty();
        }

        if(token==null)
            return crearRespuestaError("No tiene permisos para este recurso",HttpStatus.FORBIDDEN,exchange);

        return validarToken();
    }

    private Mono<Void> validarToken() {
        boolean error = true;
        boolean exception = false;

        try {
            // Validación de permisos según la URI y el rol del token
            if (requestURI.startsWith("/api/v1/")) {
                error = validarRol(List.of("Cliente"));
            }else {
                error = false;
            }

        } catch (MalformedJwtException | SignatureException e) {
            mensajeError = "El token es incorrecto";
            exception = true;
        } catch (ExpiredJwtException e) {
            mensajeError = "El token está vencido";
            exception = true;
        } catch (Exception e) {
            mensajeError = e.getMessage();
            exception = true;
        }

        return validarExcepciones(exception, error);
    }

    private Mono<Void> validarExcepciones (Boolean exception, Boolean error){
        if(exception)
            return crearRespuestaError(mensajeError,
                    HttpStatus.INTERNAL_SERVER_ERROR, exchange);

        return validarError(error);
    }

    private Mono<Void> validarError (Boolean error){
        if(error)
            return crearRespuestaError("No tiene permisos para acceder a este recurso",
                    HttpStatus.FORBIDDEN, exchange);

        return chain.filter(exchange);
    }

    private void agregarCabecerasCORS() {
        exchange.getResponse().getHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponse().getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        exchange.getResponse().getHeaders().add("Access-Control-Allow-Headers", "Origin, Accept, Content-Type, Authorization");
    }

    private boolean validarRol(List<String> rolEsperado) {
        Jws<Claims> jws = jwtUtils.parseJwt(token);
        @SuppressWarnings("unchecked")
        List<String> rolesActuales = (List<String>) jws.getPayload().get("rol");

        if (rolesActuales.stream().noneMatch(rolEsperado::contains)) {
            return true;
        }

        return false;
    }

    private String getToken(ServerHttpRequest request) {
        String header = request.getHeaders().getFirst("Authorization");

        if (header == null || !header.startsWith("Bearer "))
            return null;

        return header.replace("Bearer ", "");
    }

    private Mono<Void> crearRespuestaError(String mensaje,
                                           HttpStatus codigoError, ServerWebExchange exchange) {

        ResponseDTO dto = ResponseDTO.builder()
                .ok(false)
                .message(mensaje)
                .build();
        exchange.getResponse().setStatusCode(codigoError);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return exchange.getResponse().writeWith(Mono.just(exchange
                .getResponse().bufferFactory().wrap(toJson(dto).getBytes())));
    }

    private String toJson(ResponseDTO dto) {
        try {
            return new ObjectMapper().writeValueAsString(dto);
        } catch (Exception e) {
            return "{\"error\":\"Error al convertir el mensaje\"}";
        }
    }
}
