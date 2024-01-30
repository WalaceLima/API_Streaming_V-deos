package com.fiap.streaming_video.presentation.exception;

import io.netty.util.internal.StringUtil;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Optional;

//Serve para colocar meu GlobalException como padrão e vir primeiro e não o padrão defaault do Spring
@Order(-2)
public class GlobalExceptionHandler extends AbstractErrorWebExceptionHandler {
    public GlobalExceptionHandler(
            ErrorAttributes errorAttributes,
            WebProperties.Resources resources,
            ApplicationContext applicationContext,
            ServerCodecConfigurer serverCodecConfigurer) {
        super(errorAttributes, resources, applicationContext);
        this.setMessageWriters(serverCodecConfigurer.getWriters());
    }
    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        // Preciso dizer para qual protocolo Http eu quero usar essa exception e nesse caso é para todos
        //O método serve para server response
        return RouterFunctions.route(RequestPredicates.all(),this::formatErrorResponse);
    }
    private Mono<ServerResponse> formatErrorResponse(ServerRequest serverRequest){
        String query=serverRequest.uri().getQuery();
        ErrorAttributeOptions errorAttributeOptions=isTraceEnabled(query)? ErrorAttributeOptions.of(ErrorAttributeOptions.Include.STACK_TRACE):ErrorAttributeOptions.defaults();

        Map<String,Object> errorAttributes =getErrorAttributes((ServerRequest) serverRequest, errorAttributeOptions);

        int status= (int)Optional.ofNullable(errorAttributes.get("status")).orElse(500);
        return ServerResponse.status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(errorAttributes));
    }
    private boolean isTraceEnabled(String query){
        return !StringUtil.isNullOrEmpty(query) && query.contains("trace-true");
    }

}
