package com.insper.partida.game;

import com.insper.partida.equipe.Team;
import com.insper.partida.equipe.TeamService;
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


@ExtendWith(MockitoExtension.class)
public class GameServiceTests {
    @InjectMocks
    GameService gameService;

    @Mock
    GameRepository gameRepository;

    @Mock
    TeamService teamService;

    @Test
    void test_saveGame(){
        Game game = getGame();
        Team sao_paulo = sao_paulo();
        Team palmeiras = palmeiras();
        Mockito.when(teamService.getTeam("sao_paulo")).thenReturn(sao_paulo);
        Mockito.when(teamService.getTeam("palmeiras")).thenReturn(palmeiras);
        SaveGameDTO saveGameDTO= new SaveGameDTO();
        saveGameDTO.setAway(game.getAway());
        saveGameDTO.setHome(game.getHome());
        Assertions.assertEquals("sao_paulo",gameService.saveGame(saveGameDTO).getHome().getIdentifier());
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

    private static Team sao_paulo(){
        Team sao_paulo = new Team();
        sao_paulo.setStadium("morumbi");
        sao_paulo.setIdentifier("sao_paulo");
        sao_paulo.setName("São Paulo");
        return  sao_paulo;
    }

    private static Team palmeiras(){
        Team palmeiras = new Team();
        palmeiras.setStadium("Allianz");
        palmeiras.setIdentifier("palmeiras");
        palmeiras.setName("Palmeiras");
        return palmeiras;
    }

    private static Game getGame() {
        Game game = new Game();
        game.setIdentifier("123");
        game.setHome("sao_paulo");
        game.setAway("palmeiras");
        return game;
    }
}
