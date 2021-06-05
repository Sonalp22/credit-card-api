package com.example.creditcardapi.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CardDetailsWithPagination {
    private PaginationParams paginationData;
    private CardDetails cardDetails;
}
