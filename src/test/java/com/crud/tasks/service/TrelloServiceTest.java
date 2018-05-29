package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {
    @InjectMocks
    TrelloService trelloService;

    @Mock
    TrelloClient trelloClient;

    @Mock
    SimpleEmailService emailService;

    @Mock
    AdminConfig adminConfig;

    @Test
    public void testShouldFetchEmptyBoard() {
        //Given
        when(trelloClient.getTrelloBoards()).thenReturn(new ArrayList<>());
        //When
        List<TrelloBoardDto> resultBoardDto = trelloService.fetchTrelloBoards();
        //Then
        Assert.assertTrue(resultBoardDto.isEmpty());
    }

    @Test
    public void shouldFetchBoard() {
        //Given
        List<TrelloListDto> lists = new ArrayList<>();
        TrelloListDto trelloListDto = new TrelloListDto("id", "name", true);
        lists.add(trelloListDto);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("id", "name", lists);
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(trelloBoardDto);
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtos);

        //When
        List<TrelloBoardDto> resultBoardDto = trelloService.fetchTrelloBoards();

        //Then
        Assert.assertFalse(resultBoardDto.isEmpty());
    }

    @Test
    public void testShouldCreateTrelloCardAndSendMail() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "description", "pos", "1");
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(new CreatedTrelloCardDto());

        //When
        CreatedTrelloCardDto trelloCard = trelloService.createTrelloCard(trelloCardDto);

        //Then
        Mockito.verify(emailService, times(1)).send(anyObject());
        Assert.assertTrue(trelloCard instanceof CreatedTrelloCardDto);
    }

    @Test
    public void testShouldNotCreateTrelloCardAndNotSendMail() {
        //Given
        TrelloCardDto trelloCardDto = null;
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(null);

        //When
        CreatedTrelloCardDto trelloCard = trelloService.createTrelloCard(trelloCardDto);

        //Then
        Mockito.verify(emailService, times(0)).send(anyObject());
        Assert.assertNull(trelloCard);
    }
}