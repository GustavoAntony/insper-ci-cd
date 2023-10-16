package com.insper.partida.game;

import com.insper.partida.equipe.Team;
import com.insper.partida.equipe.TeamRepository;
import com.insper.partida.equipe.TeamService;
import com.insper.partida.equipe.dto.TeamReturnDTO;
import com.insper.partida.game.dto.EditGameDTO;
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

import static org.hamcrest.core.IsInstanceOf.any;

@ExtendWith(MockitoExtension.class)
public class GameServiceTests {
    @InjectMocks
    GameService gameService;

    @Mock
    GameRepository gameRepository;

    @Test
    void test_saveGame(){
        Game game = getGame();
        Mockito.when(gameRepository.save(game)).thenReturn(game);
        SaveGameDTO saveGameDTO= new SaveGameDTO();
        saveGameDTO.setAway(game.getAway());
        saveGameDTO.setHome(game.getHome());
        Assertions.assertEquals("sao_paulo",gameService.saveGame(saveGameDTO).getHome().getName());
    }

    @Test
    public void testEditGame() {
        // Implement a test case for the simplest scenario where the game exists.
        // Mock the necessary dependencies (gameRepository).
        Game game = getGame();
        Mockito.when(gameRepository.findByIdentifier("123")).thenReturn(game);
        Mockito.when(gameRepository.save(game)).thenReturn(game);

        EditGameDTO editGameDTO = new EditGameDTO();
        editGameDTO.setScoreHome(1);
        editGameDTO.setScoreAway(2);
        editGameDTO.setAttendance(1000);

        GameReturnDTO result = gameService.editGame("gameIdentifier", editGameDTO);

        Assertions.assertNotNull(result);
        // Add more assertions as needed.
    }

    private static Game getGame() {
        Game game = new Game();
        game.setIdentifier("123");
        game.setHome("sao_paulo");
        game.setAway("corinthians");
        return game;
    }
}
