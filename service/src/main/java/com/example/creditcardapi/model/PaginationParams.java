package com.example.creditcardapi.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
public class PaginationParams {

    @NotNull
    private String firstPage;

    private String previousPage;

    @NotNull
    private String currentPage;

    private String nextPage;

    @NotNull
    private String lastPage;

}
