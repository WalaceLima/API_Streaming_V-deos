package com.fiap.streaming_video.domain.mapper;

import com.fiap.streaming_video.domain.Video;
import com.fiap.streaming_video.domain.requestDto.RequestVideoDTO;
import com.fiap.streaming_video.domain.responseDto.ResponseVideoDto;

public class VideoMapper {
    public static Video toRequestVideo(RequestVideoDTO requestVideoDTO){
        Video video= new Video();
        video.setTitulo(requestVideoDTO.getTitulo());
        video.setDescricao(requestVideoDTO.getDescricao());
        video.setUrl(requestVideoDTO.getUrl());
        video.setDataPublicacao(requestVideoDTO.getDataPublicacao());
        return video;
    }

    public static ResponseVideoDto toResponseVideo(Video video){
        ResponseVideoDto responseVideo= new ResponseVideoDto();
        responseVideo.setTitulo(video.getTitulo());
        responseVideo.setDescricao(video.getDescricao());
        responseVideo.setUrl(video.getUrl());
        responseVideo.setDataPublicacao(video.getDataPublicacao());
        return responseVideo;
    }
}
