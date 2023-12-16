package com.fiap.streaming_video.application;

import com.fiap.streaming_video.domain.Video;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VideoCasoDeUso {
    Mono<Video> createOrIpdateVideo(Video video);
    Flux<Video> getAllVideos(Pageable pageable);
    Flux<Video> getVideosByTitle(String titulo);
    Mono<Video> deleteVideo(String id);
}
