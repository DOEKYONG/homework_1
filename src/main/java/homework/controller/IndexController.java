package homework.controller;

import homework.dto.BoardDto;
import homework.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@Controller
public class IndexController { // 타임리프 없이 시작페이지 반환하는방법 모르겠음
    @Autowired
    BoardService boardservice;
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;
    @GetMapping("/")
    public String index() {
        return "main";
    }




    @PostMapping("/boardwrite")
    @ResponseBody
    public void save(BoardDto boardDto) throws Exception {
         boardservice.write(boardDto);
         response.sendRedirect("/list");
    }


    @GetMapping("/list")

    public String getlist(Model model) {
       ArrayList<BoardDto> blist = boardservice.getboardlist();
       model.addAttribute("blist",blist);
        return "list";
    }

    @GetMapping("/view/{bno}")
    public String view(@PathVariable("bno") int bno , Model model){
        request.getSession().setAttribute("bno",bno);
        BoardDto boardDto = boardservice.getboard(bno);
        model.addAttribute("board",boardDto);
        System.out.println("세션값" + bno);
        return "view";
    }

    // 업데이트 페이지이동
    @GetMapping("/update")
    public String goupdate()
    {
        return "update";}

    //업데이트 메소드
    @PostMapping("/boardupdate")
    @ResponseBody
    public boolean boardupdate(BoardDto boardDto) {
        try{
            int bno = (Integer)request.getSession().getAttribute("bno");
            boardDto.setBno(bno);
            System.out.println(boardDto.toString());
            boolean result = boardservice.update( boardDto );
            if(result) {
                response.sendRedirect("/view/"+bno);
            }

        } catch (Exception e) {}
    return false;
    }

    // 삭제 메소드
//    @PostMapping("/delete")
//    @ResponseBody
//    public boolean delete(BoardDto boardDto) {
//       int bno = Integer.parseInt(request.getParameter("bno")) ;
//       boardDto.setBno(bno);
//        return boardservice.delete(boardDto.getBno());
//
//    }

    @PostMapping("/delete")
    @ResponseBody
    public boolean delete() throws Exception {
        int bno= (Integer)request.getSession().getAttribute("bno");
        boolean result =  boardservice.delete(bno);
       if(result) {
           response.sendRedirect("/list");
       }
       return false;
    }


// 깃34
}

