package com.fiap.streaming_video.domain;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document(collection = "VideoCollection")
public class Video {
    @Id
    private String id;
    @NotBlank(message = "Título não pode ser nulo")
    private String titulo;
    @NotBlank(message = "Descricao não pode ser nulo")
    private String descricao;
    @NotBlank(message = "Url não pode ser nulo")
    private String url;

    private LocalDateTime dataPublicacao;

    public Video(String id, String titulo, String descricao, String url, LocalDateTime dataPublicacao) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
        this.dataPublicacao = dataPublicacao;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", url='" + url + '\'' +
                ", dataPublicacao=" + dataPublicacao +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDateTime dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }
}
