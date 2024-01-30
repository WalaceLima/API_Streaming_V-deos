package com.fiap.streaming_video.application;

import com.fiap.streaming_video.domain.Video;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VideoCasoDeUsoService {
    Mono<Video> createOrUpdateVideo(Video video);
    Flux<Video> findAllVideos();
    Mono<Video> findByIdVideo(String id);
    Mono<Void> deleteVideo(String id);
    Mono<Void> updateVideo(Video video);
}
