package com.fiap.streaming_video.domain.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVideoDto {

    private String id;

    private String titulo;

    private String descricao;

    private String url;

    private LocalDate dataPublicacao;
}
