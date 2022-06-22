package homework.Dao;

import homework.dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;

//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository // 정확히 무엇을 뜻하는지, 엔티티 아닌데 써야하는이유?
public class BoardDao {

//    @Autowired
//    JdbcTemplate template; // implementation 'org.springframework.boot:spring-boot-starter-jdbc' 그레이들에 추가


//  쌩으로쓰기? application.properties 에 연결대신 Dao에서 연결 예외처리 필수 - > 서비스,컨트롤에서도 예외처리 필요함
//    public void write(BoardDto boardDto) throws ClassCastException, SQLException{
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/homework","root","12341234");
//
//            PreparedStatement ps = con.prepareStatement(
//                    "insert into board(btitle, bcontent) values(?,?)");
//            ps.setString(1,boardDto.getBtitle());
//            ps.setString(2, boardDto.getBcontent());
//            ps.executeUpdate();
//            ps.close();
//            con.close();
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }

    // JdbcTemplate 사용 => application.properites에만 연결 시키면  Connection,PreparedStatment, 예외처리 생략가능??
//    public void write(BoardDto boardDto) {
//
//            template.update("INSERT INTO board(btitle,bcontent) VALUES(?,?)", boardDto.getBtitle(),boardDto.getBcontent());
//                    // update =>template 자체 메소드
//
//    }
//
    protected Connection con;
    protected ResultSet rs;
    protected PreparedStatement ps;
    public BoardDao () {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/homework","root","12341234");

        }catch (Exception e){}



    }
    public void write(BoardDto boardDto) {
        String sql = "insert into board(btitle, bcontent) values(?,?) ";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1,boardDto.getBtitle());
            ps.setString(2,boardDto.getBcontent());
            ps.executeUpdate();

        } catch (Exception e) {System.out.println(e);}
    }

    public ArrayList<BoardDto> getboardlist() {
        ArrayList<BoardDto> boardlist = new ArrayList<BoardDto>();
        // 내림차순
        String sql = "select * from board order by bno desc";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while( rs.next() ) {
                BoardDto board = new BoardDto(
                        rs.getInt(1),rs.getString(2),
                        rs.getString(3));
                boardlist.add(board);
            }
            return boardlist;
        }catch (Exception e) { System.out.println( e );} return null;
    }

    public BoardDto getboard( int bno ) {
        String sql ="select * from board where bno="+bno;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if( rs.next() ) {
                BoardDto boardDto = new BoardDto(
                        rs.getInt(1),rs.getString(2),
                        rs.getString(3)
                );
                return boardDto;
            }
        }catch (Exception e) {} return null;


    }



}
