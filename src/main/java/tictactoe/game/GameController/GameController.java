package tictactoe.game.GameController;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tictactoe.game.Model.TableCell;
import tictactoe.game.Repositories.ITableRepository;
import tictactoe.game.Repositories.TableArrayListRepository;

@Controller
public class GameController {

    ITableRepository tableRepository;

    public GameController() {

        // Change the "new object" to the repository you want to use
        // (e.g StudentsFileRepository() or StudentsArrayListRepository() or StudentsDbRepository())

        tableRepository = new TableArrayListRepository();
    }

    @GetMapping("/")
    public String index(Model model){




        model.addAttribute("tableRows", tableRepository.readAll());
        return "index";


    }


    @PostMapping("/")
    public String create(@ModelAttribute TableArrayListRepository table) {
        System.out.println(table);
        System.out.println(tableRepository);
        return "redirect:/";

    }

    @GetMapping("/move")
    public String details(@RequestParam("rowid") int rowId, @RequestParam("collumnid") int collumnId, Model model) {

        System.out.println(rowId);
        System.out.println(collumnId);
        return "redirect:/";
    }







}
