package com.example.creditcardapi.controller;

import com.example.creditcardapi.model.CardAdditionAcknowledgement;
import com.example.creditcardapi.model.CardDetail;
import com.example.creditcardapi.model.CardDetails;
import com.example.creditcardapi.model.CardDetailsWithPagination;
import com.example.creditcardapi.model.PaginationParams;
import com.example.creditcardapi.service.CardDetailsService;
import com.example.creditcardapi.stub.StubGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CardDetailsController.class)
@AutoConfigureMockMvc(addFilters = false)
class CardDetailsControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private CardDetailsController cardDetailsController;

    @MockBean
    private CardDetailsService cardDetailsService;

    @Nested
    class CardDetailsAdd {

        @Test
        void storeCardDetailSuccess() throws Exception {
            given(cardDetailsService.addCardDetails(StubGenerator.getCardModel())).willReturn(CardAdditionAcknowledgement.builder()
                    .cardCreationId("CardId-123")
                    .build());
            mockMvc.perform(MockMvcRequestBuilders.post("/card-detail/add")
                    .header("Authorization", "BasicAuth aosisduo")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(getCardData("3695823750980000"))
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated());
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "123",
                "1234567890123",
                "123SDSDDS£@£@"
        })
        void storeWithIncorrectCardNumber(String cardNumber) throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.post("/card-detail/add")
                    .header("Authorization", "BasicAuth aosisduo")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(getCardData(cardNumber))
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("BAD_REQUEST"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.subErrors[0].message").value("Card Number must be between 16 to 19 digits only"));
        }

        @Test
        void wrongURL() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.post("/card-detals/data")
                    .header("Authorization", "BasicAuth aosisduo")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(getCardData("892349899"))
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());
        }
    }


    @Test
    void getCardDetailsSuccess() throws Exception {
        given(cardDetailsService.getCardDetails("1", 10)).willReturn(CardDetailsWithPagination.builder()
                .cardDetails(CardDetails.builder().cardDetails(List.of(CardDetail.builder().customerName("Cust1").cardNumber("Card1").limit("£1200").balance("£1100").build(),
                        CardDetail.builder().customerName("Cust2").cardNumber("Card2").limit("£1200").balance("£1100").build()))
                        .build())
                .paginationData(PaginationParams.builder().currentPage("1").firstPage("1").totalNoOfRecords("100").nextPage("2").previousPage("1").build())
                .build());
        mockMvc.perform(MockMvcRequestBuilders.get("/card-details")
                .header("Authorization", "BasicAuth aosisduo")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().stringValues("Total-Records", "100"));

    }

    private String getCardData(String cardNumber) {
        return "{\n" +
                "    \"customerName\": \"Customer1\",\n" +
                "    \"cardNumber\":" + "\"" + cardNumber + "\"" + "\n" +
                "}";
    }

}
