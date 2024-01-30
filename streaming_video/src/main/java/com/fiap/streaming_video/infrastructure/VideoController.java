package com.fiap.streaming_video.infrastructure;

import com.fiap.streaming_video.application.VideoCasoDeUsoImplService;
import com.fiap.streaming_video.domain.Video;
import com.fiap.streaming_video.domain.mapper.VideoMapper;
import com.fiap.streaming_video.domain.requestDto.RequestVideoDTO;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/video")
public class VideoController {

   private final VideoCasoDeUsoImplService videoCasoDeUsoImplService;

    @GetMapping(value = "/", produces = "application/stream+json")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Video> getAllVideos() {
      log.info("Buscando Videos!");
        return videoCasoDeUsoImplService.findAllVideos().delayElements(Duration.ofSeconds(1));
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Video> getVideoId(@PathVariable("id") String id) {
        return videoCasoDeUsoImplService.findByIdVideo(id)
                .switchIfEmpty(Mono.error(new Exception(id)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<String> save(@Valid  @RequestBody RequestVideoDTO requestVideoDTO) {
        try {
            Video video = VideoMapper.toRequestVideo(requestVideoDTO);
            return videoCasoDeUsoImplService.createOrUpdateVideo(video)
                    .map(resposta -> "Criado com sucesso: " + resposta.getTitulo())
                    .defaultIfEmpty("Erro no preenchiento dos dados");
    }catch(ConstraintViolationException constraintViolationException){
        return Mono.just("Erro de validação: "+constraintViolationException.getMessage());
    }catch (Exception exception){
            return Mono.just("Erro interno: "+exception.getMessage());
        }
}

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> update(@PathVariable String id, @Valid  @RequestBody Video video){
        return videoCasoDeUsoImplService.updateVideo(video.withId(video.getId()));
    }
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
