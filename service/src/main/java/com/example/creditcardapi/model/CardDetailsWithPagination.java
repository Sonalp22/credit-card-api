package com.example.creditcardapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CardDetailsWithPagination
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardDetailsWithPagination {
    private PaginationParams paginationData;
    private CardDetails cardDetails;
}
