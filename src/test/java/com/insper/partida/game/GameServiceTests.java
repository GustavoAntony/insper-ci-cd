package com.insper.partida.game;

import com.insper.partida.equipe.Team;
import com.insper.partida.equipe.TeamRepository;
import com.insper.partida.equipe.TeamService;
import com.insper.partida.equipe.dto.TeamReturnDTO;
import com.insper.partida.game.dto.GameReturnDTO;
import com.insper.partida.game.dto.SaveGameDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class GameServiceTests {
    @InjectMocks
    GameService gameService;

    @Mock
    GameRepository gameRepository;

    @Test
    void test_saveGame(){
        Game game = getGame();
        SaveGameDTO saveGameDTO= new SaveGameDTO();
        saveGameDTO.setAway(game.getAway());
        saveGameDTO.setHome(game.getHome());
        Assertions.assertEquals("game-1",gameService.saveGame(saveGameDTO).getIdentifier());
    }

    private static Game getGame() {
        Game game = new Game();
        game.setHome("sao_paulo");
        game.setAway("corinthians");
        return game;
    }
}
