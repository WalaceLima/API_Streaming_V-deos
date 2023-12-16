package com.fiap.streaming_video.application;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class VideoCasoDeUsoImpl{

    private static final String FORMAT="classPath:videos/%.mp4";
    @Autowired
    private ResourceLoader resourceLoader;

public Mono<Resource> getVideo(String title){
    return Mono.fromSupplier(()->resourceLoader
            .getResource(String.format(FORMAT,title)));
}
}
/*@Service
@AllArgsConstructor
public class VideoCasoDeUsoImpl implements VideoCasoDeUso{
    private final VideoRepository videoRepository;
    @Override
    public Mono<Video> createOrIpdateVideo(Video video) {
        return null;
    }

    @Override
    public Flux<Video> getAllVideos(Pageable pageable) {
        return null;
    }

    @Override
    public Flux<Video> getVideosByTitle(String titulo) {
      //  return (String.format(titulo));
        return null;
    }

    @Override
    public Mono<Video> deleteVideo(String id) {
        return null;
    }
}*/
