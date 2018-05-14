package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;

    private String listId = "123";
    private String listName = "TestList";
    private boolean listIsClosed = false;
    private String boardName = "TestBoard";
    private String boardId = "321";
    private String cardName = "CardName";
    private String cardDesc = "CardDesc";
    private String cardPos = "CardPos";
    private String cardListId = "IdOfList9212284";

    @Test
    public void testMapToBoards() {
        //Given
        List<TrelloListDto> lists = new ArrayList<>();
        lists.add(new TrelloListDto(listId, listName, listIsClosed));

        List<TrelloBoardDto> trelloBoardDto = new ArrayList<>();
        trelloBoardDto.add(new TrelloBoardDto(boardId, boardName, lists));

        //When
        List<TrelloBoard> boardListDto = trelloMapper.mapToBoards(trelloBoardDto);

        //Then
        Assert.assertEquals(1, boardListDto.get(0).getLists().size());
        Assert.assertEquals(boardId, boardListDto.get(0).getId());
        Assert.assertEquals(boardName, boardListDto.get(0).getName());
    }


    @Test
    public void testMapToBoardsDto() {
        //Given
        List<TrelloList> lists = new ArrayList<>();
        lists.add(new TrelloList(listId, listName, listIsClosed));

        List<TrelloBoard> trelloBoard = new ArrayList<>();
        trelloBoard.add(new TrelloBoard(boardId, boardName, lists));

        //When
        List<TrelloBoardDto> boardListDto = trelloMapper.mapToBoardsDto(trelloBoard);

        //Then
        Assert.assertEquals(1, boardListDto.get(0).getLists().size());
        Assert.assertEquals(boardId, boardListDto.get(0).getId());
        Assert.assertEquals(boardName, boardListDto.get(0).getName());
    }

    @Test
    public void testMapToList() {
        //Given
        List<TrelloListDto> lists = new ArrayList<>();
        lists.add(new TrelloListDto(listId, listName, listIsClosed));

        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(lists);

        //Then
        Assert.assertEquals(listId, trelloLists.get(0).getId());
        Assert.assertEquals(listName, trelloLists.get(0).getName());
        Assert.assertEquals(listIsClosed, trelloLists.get(0).isClosed());
    }

    @Test
    public void testMapToListDto() {
        //Given
        List<TrelloList> lists = new ArrayList<>();
        lists.add(new TrelloList(listId, listName, listIsClosed));

        //When
        List<TrelloListDto> trelloLists = trelloMapper.mapToListDto(lists);

        //Then
        Assert.assertEquals(listId, trelloLists.get(0).getId());
        Assert.assertEquals(listName, trelloLists.get(0).getName());
        Assert.assertEquals(listIsClosed, trelloLists.get(0).isClosed());
    }

    @Test
    public void testMapToCard() {
        //Given

        TrelloCardDto trelloCardDto = new TrelloCardDto(cardName, cardDesc, cardPos, cardListId);

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        Assert.assertEquals(cardName, trelloCard.getName());
        Assert.assertEquals(cardDesc, trelloCard.getDescription());
        Assert.assertEquals(cardPos, trelloCard.getPos());
        Assert.assertEquals(cardListId, trelloCard.getListId());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard(cardName, cardDesc, cardPos, cardListId);

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        Assert.assertEquals(cardName, trelloCardDto.getName());
        Assert.assertEquals(cardDesc, trelloCardDto.getDescription());
        Assert.assertEquals(cardPos, trelloCardDto.getPos());
        Assert.assertEquals(cardListId, trelloCardDto.getListId());
    }
}