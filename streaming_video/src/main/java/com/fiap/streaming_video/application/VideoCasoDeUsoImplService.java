package com.fiap.streaming_video.application;


import com.fiap.streaming_video.domain.Video;
import com.fiap.streaming_video.infrastructure.VideoRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*@Service
public class VideoCasoDeUsoImpl{

    private static final String FORMAT="classPath:videos/%.mp4";
    @Autowired
    private ResourceLoader resourceLoader;

public Mono<Resource> getVideo(String title){
    return Mono.fromSupplier(()->resourceLoader
            .getResource(String.format(FORMAT,title)));
}
}*/
@Log4j2
@Service
public class VideoCasoDeUsoImplService implements VideoCasoDeUsoService {
     @Autowired
     VideoRepository videoRepository;

    @Override
    public Mono<Video> createOrUpdateVideo(Video video) {
        return videoRepository.save(video);
    }

    @Override
    public Flux<Video> findAllVideos() {
        return videoRepository.findAll();
    }

    @Override
    public Mono<Video> findByIdVideo(String id) {

        return videoRepository.findById(id);
    }

    @Override
    public Mono<Void> deleteVideoById(String id) {
        return videoRepository.deleteById(id);
    }

    @Override
    public Mono<Video> updateVideoById(String id) {
        return null;
    }
}
