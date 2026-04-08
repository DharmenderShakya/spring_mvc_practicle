package com.srping_mvc_json_view_practicle.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {

    @NotNull
    private Long userId;

    @NotNull
    private String product;

    @NotNull
    @Positive
    private Double total;

    @NotNull
    private String status;

}