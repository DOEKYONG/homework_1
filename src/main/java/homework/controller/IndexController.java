package homework.controller;

import homework.dto.BoardDto;
import homework.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

// 깃
}

