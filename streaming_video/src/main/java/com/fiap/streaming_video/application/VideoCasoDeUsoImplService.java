package com.fiap.streaming_video.application;

import com.fiap.streaming_video.domain.Video;
import com.fiap.streaming_video.infrastructure.VideoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Log4j2
@Service
@RequiredArgsConstructor
public class VideoCasoDeUsoImplService implements VideoCasoDeUsoService {

    private final VideoRepository videoRepository;


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

        return videoRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST,"Video não localizado")));
    }

    public Mono<Void> deleteVideo(String id) {
        return videoRepository.deleteById(id);
    }


    @Override
    public Mono<Void> updateVideo(Video video) {
        return findByIdVideo(video.getId())
                .map(video1 -> video.withId(video1.getId()))
                .flatMap(videoRepository::save)
                .thenEmpty(Mono.empty());
      //  return videoRepository.updateVideo(video);
    }

}
