package com.example.creditcardapi.service;

import static org.mockito.Mockito.when;

import com.example.creditcardapi.domain.CardDetail;
import com.example.creditcardapi.exceptions.BadRequestException;
import com.example.creditcardapi.exceptions.DataNotFoundException;
import com.example.creditcardapi.mapper.CardDetailsMapper;
import com.example.creditcardapi.model.CardAdditionAcknowledgement;
import com.example.creditcardapi.model.CardDetailsWithPagination;
import com.example.creditcardapi.repository.CardDetailsRepository;
import com.example.creditcardapi.stub.StubGenerator;
import org.junit.After;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class CardDetailsServiceImplTest {

    private CardDetailsService cardDetailsService;

    @MockBean
    private CardDetailsRepository cardDetailsRepository;

    @InjectMocks
    private CardDetailsMapper cardDetailsMapper;

    @BeforeEach
    public void setup() {
        this.cardDetailsService= new CardDetailsServiceImpl(cardDetailsRepository, cardDetailsMapper);
        when(cardDetailsRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(StubGenerator.getCardDetailBEPage());
        when(cardDetailsRepository.save(Mockito.any(CardDetail.class))).thenReturn(StubGenerator.getCardDetailBE());
    }

    @AfterEach
    public void tearDown() {
        this.cardDetailsService = null;
    }

    @Test
    public void getCardDetailsPaginatedData_Success(){

        CardDetailsWithPagination cardDetailsWithPagination = cardDetailsService.getCardDetails("1",2);
        Assertions.assertNotNull(cardDetailsWithPagination);
        Assertions.assertEquals(6,cardDetailsWithPagination.getCardDetails().getCardDetails().size());
        Assertions.assertEquals("6",cardDetailsWithPagination.getPaginationData().getTotalNoOfRecords());
        Assertions.assertEquals("1",cardDetailsWithPagination.getPaginationData().getCurrentPage());
        Assertions.assertEquals("1",cardDetailsWithPagination.getPaginationData().getFirstPage());

    }

    @Test
    public void getCardDetailsPaginatedData_Failure(){

        when(cardDetailsRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(StubGenerator.getCardDetailBEPageEmpty());

        Assertions.assertThrows(DataNotFoundException.class, () -> cardDetailsService.getCardDetails("1",2));

    }

    @Test
    public void addCardData_Failure(){
       com.example.creditcardapi.model.CardDetail cardDetailModelData = com.example.creditcardapi.model.CardDetail
               .builder()
               .customerName("Cust1").cardNumber("83764091801224").limit("£737")
               .build();
        Assertions.assertThrows(BadRequestException.class, () -> cardDetailsService.addCardDetails(cardDetailModelData));

    }

    @Test
    public void addCardData_Success(){
        com.example.creditcardapi.model.CardDetail cardDetailModelData = com.example.creditcardapi.model.CardDetail
                .builder()
                .customerName("Cust1").cardNumber("5555555555554444").limit("£737")
                .build();
        CardAdditionAcknowledgement updatedId = cardDetailsService.addCardDetails(cardDetailModelData);
        Assertions.assertEquals("1123",updatedId.getCardCreationId());

    }

}
