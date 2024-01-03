package com.fiap.streaming_video.infrastructure;

import com.fiap.streaming_video.application.VideoCasoDeUsoImplService;
import com.fiap.streaming_video.domain.Video;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping(value = "/video")
public class VideoController {
        @Autowired
        VideoCasoDeUsoImplService videoCasoDeUsoImplService;

    @GetMapping(value="/",produces = "application/stream+json")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Video> getAllVideos(){
        return videoCasoDeUsoImplService.findAllVideos().delayElements(Duration.ofSeconds(1));
    }
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Video> getVideoId(@PathVariable("id") String id){
        return videoCasoDeUsoImplService.findByIdVideo(id)
                .switchIfEmpty(Mono.error(new Exception(id)));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Video> save(@RequestBody @Valid Video video){
        return videoCasoDeUsoImplService.createOrUpdateVideo(video);
    }
/*    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> update(@PathVariable String id){
        return videoCasoDeUsoImplService.updateVideo(id);
    }*/
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable String id){
        return videoCasoDeUsoImplService.deleteVideo(id);
    }

    //Função Get, irá trazer os objetos de forma assícrona, sem obstruir o serviço e que funciona de forma independente
@GetMapping(value = "/events",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Tuple2<Long,Video>> getVideoByEvents(){
        Flux<Long> inteval=Flux.interval(Duration.ofSeconds(10));
        Flux<Video> events=videoCasoDeUsoImplService.findAllVideos();
        System.out.println("Lista criada por aqui");
        return Flux.zip(inteval,events);
    }
@ResponseStatus(HttpStatus.BAD_REQUEST)
@ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex){
        Map<String,String> errors=new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName=((FieldError)error).getField();
            String errorMessage= error.getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });
        return errors;
    }
}
