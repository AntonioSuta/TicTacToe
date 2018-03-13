package tictactoe.game.Model;

public class User {

    int id = 0;
    String username;
    String password;
    int wins = 0;
    int losses= 0;
    int ties= 0;

    public User(){}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(int id, String username, String password, int wins, int losses, int ties) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.wins = wins;
        this.losses = losses;
        this.ties = ties;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getTies() {
        return ties;
    }

    public void setTies(int ties) {
        this.ties = ties;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
