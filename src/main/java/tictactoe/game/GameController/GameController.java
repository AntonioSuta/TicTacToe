package tictactoe.game.GameController;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tictactoe.game.Model.TableCell;
import tictactoe.game.Model.TableRow;
import tictactoe.game.Model.User;
import tictactoe.game.Repositories.ITableRepository;
import tictactoe.game.Repositories.IUserRepository;
import tictactoe.game.Repositories.TableArrayListRepository;
import tictactoe.game.Repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class GameController {

    ITableRepository tableRepository;
    IUserRepository userRepository;
    User currentUser;
    String message = "";

    public GameController() {

        // Change the "new object" to the repository you want to use
        // (e.g StudentsFileRepository() or StudentsArrayListRepository() or StudentsDbRepository())

        tableRepository = new TableArrayListRepository();
        userRepository = new UserRepository();
    }

    @GetMapping("/")
    public String loginPage(Model model) {
        return "login";
    }

    @PostMapping("/")
    public String login(@ModelAttribute User user) {
        User dbUser = userRepository.read(user.getUsername(), user.getPassword());
        currentUser = dbUser;
        if (dbUser.getUsername().equals(user.getUsername()) && dbUser.getPassword().equals(user.getPassword())) {
            return "redirect:/play";
        } else {
            return "redirect:/";
        }


    }

    @GetMapping("/signup")
    public String signup(Model model) {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute User user) {
        userRepository.create(user);
        return "redirect:/";

    }


    @GetMapping("/play")
    public String index(Model model) {

        if(!message.isEmpty()){
            return "redirect:/message";
        }
        model.addAttribute("tableRows", tableRepository.readAll());
        return "index";


    }

    @GetMapping("/message")
    public String message(Model model){
        model.addAttribute("message", message);
        return "message";
    }

    @GetMapping("middle")
    public String middle(Model model){
        message = "";
        return "redirect:/play";
    }


    @GetMapping("/move")
    public String details(@RequestParam("rowId") int rowId, @RequestParam("columnId") int columnId, Model model) {
        move(rowId, columnId);
        return "redirect:/play";
    }

    public void move(int row, int column) {
        message = "";
        this.tableRepository.readAll().get(row).getRow().get(column).setCellValue("X");
        winningHandler();
        if (checkForWin()==0 || checkForWin()==3){
            return;
        }
        String[][] table = getTable();
        List<Integer> rowList = new ArrayList<Integer>();
        List<Integer> colList = new ArrayList<Integer>();

        for (int i=0; i < 3; i++){
            for (int j=0; j<3; j++){
                if (table[i][j].equals("")){
                    rowList.add(i);
                    colList.add(j);
                }
            }
        }

        Random random = new Random();
        int choice = random.nextInt(rowList.size())+0;
        this.tableRepository.readAll().get(rowList.get(choice)).getRow().get(colList.get(choice)).setCellValue("0");

        winningHandler();
    }

    public void winningHandler(){
        switch (checkForWin()){
            case 0: userWin(); break;
            case 1: computerWin(); break;
            case 3: tie();break;
            case 4: return;
        }
    }

    public void userWin(){
        currentUser.setWins(currentUser.getWins()+1);
        message = "You won!";
        userRepository.update(currentUser);
        tableRepository = new TableArrayListRepository();
    }

    public void computerWin(){
        currentUser.setLosses(currentUser.getLosses()+1);
        message= "You lose!";
        userRepository.update(currentUser);
        tableRepository = new TableArrayListRepository();
    }

    public void tie(){
        currentUser.setTies(currentUser.getTies()+1);
        System.out.println("It's a tie.");
        userRepository.update(currentUser);
        tableRepository = new TableArrayListRepository();
    }

    public String[][] getTable() {
        String values[][] = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                values[i][j] = this.tableRepository.readAll().get(i).getRow().get(j).getCellValue();
            }
        }
        return values;
    }

    public int checkForWin() {
        String table[][]= getTable();
        boolean empty=true;
        int count=0;
        for (int i=0;i<3;i++){
            for (int j= 0; j<3;j++){
                if(table[i][j].equals("")){
                    count++;
                }
            }
        }
        if (count<1){
            empty = false;
        }
        String winner = "7";
//involving table[0][0]
        if	(table[0][0].equals(table[0][1]) && table[0][0].equals(table[0][2]) ||			//top row
                table[0][0].equals(table[1][0]) && table[0][0].equals(table[2][0]) ||			//left column
                table[0][0].equals(table[1][1]) && table[0][0].equals(table[2][2])){				//left-down diagonal
            winner = table[0][0];}

//involving table[0][1]
        else if (table[0][1].equals(table[1][1]) && table[0][1].equals(table[2][1])){		//middle column
            winner = table[0][1];}

//involving table[0][2]
        else if (table[0][2].equals(table[1][2]) && table[0][2].equals(table[2][2]) ||		//right column
                table[0][2].equals(table[1][1]) && table[0][2].equals(table[2][0])){			//right-down diagonal
            winner = table[0][2];}

//involving table[1][0]
        else if (table[1][0].equals(table[1][1]) && table[1][0].equals(table[1][2])){		//middle row
            winner = table[1][0];}

//involving table[2][0]
        else if (table[2][0].equals(table[2][1]) && table[2][0].equals(table[2][2])){		//bottom row
            winner = table[2][0];}

        if (winner.equals("X"))
        {
            return 0;
        }
        else if (winner.equals("0"))
        {
            return 1;
        }
        else if (!empty)
        {
            return 3;
        }
else
        {
            return 4;
        }
    }









}
