package com.fiap.streaming_video;

import com.fiap.streaming_video.application.VideoCasoDeUsoImplService;
import com.fiap.streaming_video.domain.Video;
import com.fiap.streaming_video.domain.requestDto.RequestVideoDTO;
import com.fiap.streaming_video.infrastructure.VideoController;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;

import static org.mockito.ArgumentMatchers.any;
import static reactor.core.publisher.Mono.when;

@WebFluxTest(VideoController.class)
@RunWith(SpringRunner.class)
public class VideoControllerTest {

    @Autowired
    private WebTestClient webTestClient;
    @InjectMocks
    private VideoController videoController;
    @MockBean
    private VideoCasoDeUsoImplService videoCasoDeUsoImplService;

    @Test
    public void testGetAllVideos() {
        // Configurar o comportamento do serviço mockado
        Video video1 = new Video();
        video1.setId("1");
        Video video2 = new Video();
        video2.setId("2");
        when(videoCasoDeUsoImplService.findAllVideos()).thenReturn(Flux.just(video1, video2));

        // Executar o método do controlador
        Flux<Video> result = videoController.getAllVideos();

        // Verificar o resultado
        StepVerifier.create(result)
                .expectNext(video1)
                .expectNext(video2)
                .verifyComplete();
    }
    @Test
    public void testGetVideoId() {
        // Preparação do teste
        String videoId = "1";
        Video video = new Video();
        video.setId(videoId);
        when(videoCasoDeUsoImplService.findByIdVideo(videoId)).thenReturn(Mono.just(video));

        // Executar o método do controlador
        Mono<Video> result = videoController.getVideoId(videoId);

        // Verificar o resultado
        StepVerifier.create(result)
                .expectNext(video)
                .verifyComplete();
    }
    @Test
    public void testSave() {
        // Preparação do teste
        RequestVideoDTO requestVideoDTO = new RequestVideoDTO();
        // Defina os campos necessários em requestVideoDTO, por exemplo:
        requestVideoDTO.setTitulo("Titulo do vídeo");
        requestVideoDTO.setDescricao("Descrição do vídeo");
        requestVideoDTO.setUrl("www.example.com");

        Video video = new Video();
        video.setId("1");
        video.setTitulo(requestVideoDTO.getTitulo());
        video.setDescricao(requestVideoDTO.getDescricao());
        video.setUrl(requestVideoDTO.getUrl());

        when(videoCasoDeUsoImplService.createOrUpdateVideo(any(Video.class))).thenReturn(Mono.just(video));

        // Executar o método do controlador
        Mono<String> result = videoController.save(requestVideoDTO);

        // Verificar o resultado
        StepVerifier.create(result)
                .expectNext("Criado com sucesso: " + video.getTitulo())
                .verifyComplete();
    }
    @Test
    public void testUpdate() {
        // Preparação do teste
        String videoId = "1";
        Video video = new Video();
        video.setId(videoId);
        // Defina outros campos do vídeo conforme necessário...

        when(videoCasoDeUsoImplService.updateVideo(any(Video.class))).thenReturn(Mono.empty());

        // Executar o método do controlador
        Mono<Void> result = videoController.update(videoId, video);

        // Verificar o resultado
        StepVerifier.create(result)
                .verifyComplete();
    }
    @Test
    public void testDelete() {
        // Preparação do teste
        String videoId = "1";

        when(videoCasoDeUsoImplService.deleteVideo(videoId)).thenReturn(Mono.empty());

        // Executar o método do controlador
        Mono<Void> result = videoController.delete(videoId);

        // Verificar o resultado
        StepVerifier.create(result)
                .verifyComplete();
    }
    @Test
    public void testGetVideoByEvents() {
        // Preparação do teste
        Video video1 = new Video();
        video1.setId("1");
        Video video2 = new Video();
        video2.setId("2");

        when(videoCasoDeUsoImplService.findAllVideos()).thenReturn(Flux.just(video1, video2));
        when(videoCasoDeUsoImplService.findAllVideos()).thenReturn(Flux.just(video1, video2));

        // Executar o método do controlador
        Flux<Tuple2<Long, Video>> result = videoController.getVideoByEvents();

        // Verificar o resultado
        StepVerifier.create(result)
                .expectNextMatches(tuple -> tuple.getT1() >= 0 && tuple.getT2().equals(video1))
                .expectNextMatches(tuple -> tuple.getT1() >= 0 && tuple.getT2().equals(video2))
                .verifyComplete();
    }


}
