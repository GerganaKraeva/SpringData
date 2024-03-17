package bg.softuni.modelmapperexercise.service;

import bg.softuni.modelmapperexercise.service.dtos.GameAddDTO;
import bg.softuni.modelmapperexercise.service.dtos.GamesAllDTO;

import java.util.Map;
import java.util.Set;

public interface GameService {
    String addGame(GameAddDTO gameAddDTO);

    String editGame(long id, Map<String, String> map);

    String deleteGame(long id);

    Set<GamesAllDTO> getAllGames();

    String allGamesReadyToPrint();
}
