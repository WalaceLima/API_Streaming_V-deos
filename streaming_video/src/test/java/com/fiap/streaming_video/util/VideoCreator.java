package com.fiap.streaming_video.util;

import com.fiap.streaming_video.domain.Video;

import java.time.LocalDate;

public class VideoCreator {
    public static Video createVideoToBeSaved(){
        return Video.builder()
                .titulo("Teste de Fidelidade").descricao("Olá teste para criação").dataPublicacao(LocalDate.ofEpochDay(2024-12-13))
                .build();
    }
    public static Video createVideoValidVideo(){
        return Video.builder()
                .id("0123546")
                .titulo("Teste de Fidelidade").descricao("Olá teste para validação de video").dataPublicacao(LocalDate.ofEpochDay(2024-12-13))
                .build();
    }
    public static Video createValidUpdateVideo(){
        return Video.builder()
                .id("0123546")
                .titulo("Teste de Fidelidade").descricao("Olá teste para atualização de video").dataPublicacao(LocalDate.ofEpochDay(2024-12-13))
                .build();
    }
}
