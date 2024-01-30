package com.fiap.streaming_video.domain.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestVideoDTO {

    private String id;
    @NotBlank(message = "Título não pode estar vazio!")
    @NotNull
    private String titulo;
    @NotBlank(message = "Título não pode estar vazio!")
    @NotNull
    private String descricao;
    @NotBlank(message = "Título não pode estar vazio!")
    @NotNull
    private String url;
    private LocalDate dataPublicacao;
}
