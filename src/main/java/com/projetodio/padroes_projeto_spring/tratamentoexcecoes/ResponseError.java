package com.projetodio.padroes_projeto_spring.tratamentoexcecoes;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseError {

    private Date timestamp = new Date();
    private String status = "error";
    private int statusCode = 400;
    private String error;

    //getters e setters
}
