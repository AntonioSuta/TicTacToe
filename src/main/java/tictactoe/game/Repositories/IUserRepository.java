package tictactoe.game.Repositories;

import tictactoe.game.Model.User;

import java.util.ArrayList;

public interface IUserRepository {
    void create(User user);
    User read(String username, String password);
    void update(User user);




}
