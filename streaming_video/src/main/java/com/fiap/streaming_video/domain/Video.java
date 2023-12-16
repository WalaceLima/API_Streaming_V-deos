package com.fiap.streaming_video.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Video {
    private String id;
    private String titolo;
    private String descricao;
    private String url;
    private LocalDateTime dataPublicacao;
}
