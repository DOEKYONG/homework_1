package homework.controller;

import homework.dto.BoardDto;
import homework.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class IndexController { // 타임리프 없이 시작페이지 반환하는방법 모르겠음
    @Autowired
    BoardService boardservice;
    @GetMapping("/")
    public String index() {
        return "main";
    }




    @PostMapping("/boardwrite")
    @ResponseBody
    public void save(BoardDto boardDto) {
         boardservice.write(boardDto);
    }

    @GetMapping("/list")

    public String getlist(Model model) {
       ArrayList<BoardDto> blist = boardservice.getboardlist();
       model.addAttribute("blist",blist);
        return "list";
    }

    @GetMapping("/view/{bno}")
    public String view(@PathVariable("bno") int bno , Model model){
        BoardDto boardDto = boardservice.getboard(bno);
        model.addAttribute("board",boardDto);

        return "view";
    }



// 깃34
}

