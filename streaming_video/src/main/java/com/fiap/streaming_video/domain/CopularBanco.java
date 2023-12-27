//package com.fiap.streaming_video.domain;
//
//import com.fiap.streaming_video.infrastructure.VideoRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import reactor.core.publisher.Flux;
//
//import java.util.UUID;
//@Component
//public class CopularBanco implements CommandLineRunner {
//    private final VideoRepository videoRepository;
//
//    public CopularBanco(VideoRepository videoRepository) {
//        this.videoRepository = videoRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        videoRepository.deleteAll()
//                .thenMany(
//                        Flux.just("API Rest Spring Boot","Banco de Dados")
//                                .map(nome->new Video(UUID.randomUUID().toString(),nome))
//                                .flatMap(videoRepository::save)
//                ).subscribe(System.out::println);
//    }
//}
