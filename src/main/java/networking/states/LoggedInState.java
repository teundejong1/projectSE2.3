package networking.states;

import games.GameEnum;
import networking.NetworkManager;
import networking.commands.ChallengeAcceptCommand;
import networking.commands.ChallengePlayerCommand;
import networking.commands.GetGamelistCommand;
import networking.commands.GetPlayerlistCommand;
import networking.commands.LogoutCommand;
import networking.commands.SubscribeCommand;

/**
 * 
 * @author Jeroen Lammersma
 */
public class LoggedInState extends State {
    
    @Override
    public void acceptChallenge(NetworkManager manager, int challengeNumber)
            throws IllegalStateException {
        manager.sendCommand(new ChallengeAcceptCommand(challengeNumber));
    }

    @Override
    public void challengePlayer(NetworkManager manager, String opponent, GameEnum game)
            throws IllegalStateException {
        manager.sendCommand(new ChallengePlayerCommand(opponent, game));
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
    public void subscribe(NetworkManager manager, GameEnum game) throws IllegalStateException {
        manager.sendCommand(new SubscribeCommand(game));
    }
}
