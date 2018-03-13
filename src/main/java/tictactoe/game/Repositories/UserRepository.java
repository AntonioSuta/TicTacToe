package tictactoe.game.Repositories;

import tictactoe.game.Model.User;
import tictactoe.game.Repositories.utils.DbConnections;

import java.sql.*;

public class UserRepository implements IUserRepository{

    private Connection conn;
    private PreparedStatement preparedStatement;
    private ResultSet result;


    public UserRepository(){
        this.conn = DbConnections.getConnection();
    }

    @Override
    public User read(String username, String password) {

        try {
            String query = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";
            preparedStatement = conn.prepareStatement(query);
            result = preparedStatement.executeQuery();

            if (result.next()){
                return new User(result.getInt("id"), result.getString("username"), result.getString("password"), result.getInt("wins"), result.getInt("losses"), result.getInt("ties"));
            }

        } catch (SQLException e) {
            return null;
        }


        return null;
    }

    @Override
    public void create(User user) {

        try {

            preparedStatement = conn.prepareStatement("INSERT INTO users(username, password, wins, losses, ties)  VALUES (?,?,?,?,?)");
            System.out.println(user.getUsername());
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getWins());
            preparedStatement.setInt(4, user.getLosses());
            preparedStatement.setInt(5, user.getTies());

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void update(User user) {
        String query = "UPDATE users SET wins='" + user.getWins() + "', losses='"+ user.getLosses() + "', ties='" + user.getTies() + "' WHERE username='" + user.getUsername() + "'";
        System.out.println(query);
        try{
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
