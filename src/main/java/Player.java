public class Player {

    private char playerMark;
    private String name;

    public Player(char mark, String name){
        this.playerMark = mark;
        this.name = name;
    }
    public char getPlayerMark()
    {
        return playerMark;
    }

    public String getPlayerName(){
        return name;
    }

}
