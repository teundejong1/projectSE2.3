package networking.states;

import games.Move;
import networking.NetworkManager;
import networking.commands.ForfeitCommand;
import networking.commands.GetGamelistCommand;
import networking.commands.GetPlayerlistCommand;
import networking.commands.LogoutCommand;
import networking.commands.MoveCommand;

class PlayingState extends State {

    @Override
    public void forfeit(NetworkManager manager) throws IllegalStateException {
        manager.sendCommand(new ForfeitCommand());
    }

    @Override
    public void getGameList(NetworkManager manager) throws IllegalStateException {
        manager.sendCommand(new GetGamelistCommand());
    }

    @Override
    public void getPlayerList(NetworkManager manager) throws IllegalStateException {
        manager.sendCommand(new GetPlayerlistCommand());
    }

    @Override
    public void logout(NetworkManager manager) throws IllegalStateException {
        manager.sendCommand(new LogoutCommand());
        manager.setState(new LoggedOutState());
    }

    @Override
    public void sendMove(NetworkManager manager, Move move, int boardSize)
            throws IllegalStateException {
        manager.sendCommand(new MoveCommand(move, boardSize));
    }
    
}
