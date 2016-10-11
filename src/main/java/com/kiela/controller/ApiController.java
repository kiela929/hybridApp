package com.kiela.controller;


import com.kiela.domain.BoardVO;
import com.kiela.domain.Result;
import com.kiela.persistence.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by pino8 on 2016-09-12.
 */

/**
 *
 * RestController annotation은 안드로이드 등 모바일의 사용성이 높아지면서 4.x에 처음으로 도입된 기능으로,
 * 웹페이지 리턴이 아니라 json.xml 등의 Resource를 리턴해준다.
 */
@RestController
public class ApiController {
    @Autowired
    private BoardMapper boardMapper;

    @RequestMapping("/hello")
    public String Hello(){
        return "Hello test";
    }

    //게시판 생성 API
    @RequestMapping(method= RequestMethod.POST, value="/api/board")
    public Result addBoard(@RequestBody BoardVO board){

        System.out.println("board: "+board);

    /*
    만일 lombok을 사용하지 않았다면 4바이트 메모리 주소값이 프린트 될것이다.
    하지만 lombok이 toString 메서드를 오버라이딩하여서
    board안에 있는 인스턴스 멤버값을 모두 출력해준다.
     */

        boardMapper.insertBoard(board);

        return new Result(0,"success");
    }

    //게시판 글 상세 보기
    @RequestMapping(method=RequestMethod.GET, value = "/api/board/{board_id}")
    public BoardVO getBoard(@PathVariable int board_id){
        System.out.println("board_id:"+board_id);

        BoardVO board=boardMapper.findById(board_id);

        return board;
    }

    //게시판 글 목록 보기
    @RequestMapping(method=RequestMethod.GET, value="/api/board")
    public List<BoardVO> getBoardList(){
       /* BoardVO board = new BoardVO();
        board.setBoard_id(1);
        board.setTitle("제목");
        board.setContent("내용");

        ArrayList<BoardVO> boardList= new ArrayList<BoardVO>();
        boardList.add(board);

        return boardList;*/

       return boardMapper.findAll();
    }

    //게시판 글 수정하기
    @RequestMapping(method=RequestMethod.PUT, value="/api/board/{board_id}")
    public BoardVO modifyBoard(@PathVariable int board_id, @RequestBody BoardVO inBoard){
        System.out.println("board_id: "+board_id);
        BoardVO board = new BoardVO();
        board.setBoard_id(board_id);
        if(inBoard.getContent()!=null){
            board.setContent(inBoard.getContent());
        }

        return board;
    }

    //게시판 글 삭제하기
    @RequestMapping(method=RequestMethod.DELETE, value="/api/board/{board_id}")
    public Result removeBoard(@PathVariable String board_id){
        System.out.println("board_id:"+board_id);
        return new Result(0,"success");
    }
}
