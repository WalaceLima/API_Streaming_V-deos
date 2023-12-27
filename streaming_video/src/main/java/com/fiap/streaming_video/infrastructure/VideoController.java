package com.fiap.streaming_video.infrastructure;

import com.fiap.streaming_video.application.VideoCasoDeUsoImplService;
import com.fiap.streaming_video.domain.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class VideoController {
        @Autowired
        VideoCasoDeUsoImplService videoCasoDeUsoImplService;
//        @Autowired
//        private ResourceLoader resourceLoader;
//
//    @GetMapping(value = "video/{title}",produces = "video/mp4")
//    public Mono<Resource> getVideo(@PathVariable String title, @RequestHeader("Range") String range){
//        System.out.println("range in bytes : "+ range);
//        Resource resource=this.resourceLoader.getResource("classpath:video/"+title+".mp4");
//
//    //    return videoCasoDeUsoImpl. getVideo(title);
//        return Mono.fromSupplier(()->resource);
//    }
//    private final VideoCasoDeUsoService videoCasoDeUso;
//
//    @GetMapping
//    public Flux<Video> getAllVideos(@RequestParam(defaultValue = "0")int page,
//                                    @RequestParam(defaultValue = "10")int size,
//                                    @RequestParam(required = false)String titulo){
//        Pageable pageable =PageRequest.of(page,size,Sort.by("dataPublicacao").descending());
//    if(titulo!=null && !titulo.isEmpty()){
//        return videoCasoDeUso.getVideosByTitle(titulo,pageable);
//    }else {
//        return videoCasoDeUso.getAllVideos(pageable);
//    }
//    }
    @GetMapping(value="/video")
    public Flux<Video> getVideo(){

        return videoCasoDeUsoImplService.findAllVideos();
    }
    @GetMapping(value = "/{id}")
    public Mono<Video> getVideoId(@PathVariable String id){

        return videoCasoDeUsoImplService.findByIdVideo(id);
    }
    @PostMapping(value = "/video")
    public Mono<Video> save(@RequestBody Video video){

        return videoCasoDeUsoImplService.createOrUpdateVideo(video);
    }
    @DeleteMapping(value = "/{id}")
    public Mono<Void> deleteById(@PathVariable String id){
        return videoCasoDeUsoImplService.deleteVideoById(id);
    }
}
