package tictactoe.game.GameController;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController {


    @GetMapping("/")
    public void index(){

        return "index";

    }



}
