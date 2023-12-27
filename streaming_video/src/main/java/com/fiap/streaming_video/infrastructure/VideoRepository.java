package com.fiap.streaming_video.infrastructure;


import com.fiap.streaming_video.domain.Video;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface VideoRepository extends ReactiveMongoRepository<Video,String> {
}
