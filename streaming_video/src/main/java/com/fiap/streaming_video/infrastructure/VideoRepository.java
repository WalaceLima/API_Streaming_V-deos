package com.fiap.streaming_video.infrastructure;


import com.fiap.streaming_video.domain.Video;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface VideoRepository extends ReactiveMongoRepository<Video,String> {
 //   Mono<Void> updateVideo(String id);
}
