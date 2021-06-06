package com.example.creditcardapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * PaginationParams
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaginationParams {

    @NotNull
    private String firstPage;

    private String previousPage;

    @NotNull
    private String currentPage;

    private String nextPage;

    @NotNull
    private String totalNoOfRecords;

}
