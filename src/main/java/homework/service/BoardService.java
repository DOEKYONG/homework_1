package homework.service;

import homework.Dao.BoardDao;
import homework.dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class BoardService {
    @Autowired
    BoardDao boardDao;

    public void write(BoardDto boardDto){

            boardDao.write(boardDto);

    }
    public ArrayList<BoardDto>getboardlist() {
      return   boardDao.getboardlist();
    }
    public BoardDto getboard( int bno ) {
        return boardDao.getboard(bno);
    }

    public boolean update(BoardDto boardDto) {
      //  System.out.println(boardDto.toString());

       boolean result = boardDao.update(boardDto);
       return result;

    }

    public boolean delete(int bno) {

    boolean result = boardDao.delete(bno);
      //  System.out.println(bno);
        return result;

    }


}
