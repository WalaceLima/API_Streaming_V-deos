package com.fiap.streaming_video.application;

import com.fiap.streaming_video.domain.Video;
import com.fiap.streaming_video.infrastructure.VideoRepository;
import com.fiap.streaming_video.util.VideoCreator;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.blockhound.BlockingOperationError;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

@ExtendWith(SpringExtension.class)
public class VideoCasoDeUsoImplServiceTest {
    @InjectMocks
    private VideoCasoDeUsoImplService videoCasoDeUsoImplService;

@Mock
private VideoRepository videoRepositoryMock;

private final Video video= VideoCreator.createVideoValidVideo();


@Test
public void blockHoundWorks() {
try{
    FutureTask<?> task=new FutureTask<>(()->{
        Thread.sleep(0);
        return "";
    });
    Schedulers.parallel().schedule(task);
    task.get(10, TimeUnit.SECONDS);
    Assertions.fail("should fail");
} catch (Exception e) {
   Assertions.assertTrue(e.getCause() instanceof BlockingOperationError);
}
}
@BeforeEach
public void setUp(){
    BDDMockito.when(videoRepositoryMock.findAll())
            .thenReturn(Flux.just(video));


}
    @Test
    @DisplayName("FindAll return a flux of video")
    public void findAll_returnFluxOfVideo_WhenSuccessful(){
        StepVerifier.create(videoCasoDeUsoImplService.findAllVideos())
                .expectSubscription()
                .expectNext(video)
                .verifyComplete();
    }
}
