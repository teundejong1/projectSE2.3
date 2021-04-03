package player;



/**
 * Player class
 * @author Eva Jakobs, Teun de Jong
 */
public class Player {
    private char playerMark;
    private String name;

    /**
     * Constructor for class Player
     * @param mark char, used for the making of moves and to check who's turn it is.
     * @param name String, Player name.
     */
    public Player(char mark, String name){
        this.playerMark = mark;
        this.name = name;
    }

    /**
     * Method to return plauerMark.
     * @return playerMark char.
     */
    public char getPlayerMark()
    {
        return playerMark;
    }

    /**
     * Method to return Player name.
     * @return String player name.
     */
    public String getPlayerName(){
        return name;
    }
}
