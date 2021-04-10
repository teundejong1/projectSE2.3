package networking.commands;

public class LoginCommand implements Command {
    
    private String name;

    public LoginCommand(String name) {
        this.name = name;
    }

    @Override
    public void validateResponse() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String toString() {
        return String.format("LOGIN %s", name);
    }

}
