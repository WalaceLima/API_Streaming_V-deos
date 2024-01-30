package com.fiap.streaming_video.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@With
@Document(collection = "VideoCollection")
public class Video {
    @Id
    private String id;

    private String titulo;

    private String descricao;

    private String url;

    private LocalDate dataPublicacao;


}
