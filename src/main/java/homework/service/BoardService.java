package homework.service;

import homework.Dao.BoardDao;
import homework.dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class BoardService {
    @Autowired
    BoardDao boardDao;

    public void write(BoardDto boardDto){

            boardDao.write(boardDto);

    }
}
