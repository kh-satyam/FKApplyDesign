package games.TicTacToe.statistics;

import games.TicTacToe.states.TicTacToeGameStates;

import java.util.HashMap;
import java.util.Map;

public class TicTacToeGameStats {
    private HashMap<String,Integer> wins;

    public TicTacToeGameStats() {
        wins = new HashMap<>();
    }

    public void printStats() {
        for(Map.Entry<String,Integer> entry:wins.entrySet()) {
            System.out.println(entry.getKey() + "  " + entry.getValue());
        }
    }

    public void addStats(String playerName) {
        if(wins.containsKey(playerName)) {
            wins.replace(playerName,wins.get(playerName),wins.get(playerName) + 1);
        }else{
            wins.put(playerName,1);
        }
    }
}
