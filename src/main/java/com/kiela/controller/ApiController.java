package com.kiela.controller;


import com.kiela.domain.BoardVO;
import com.kiela.domain.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/hello")
    public String Hello(){
        return "Hello test";
    }

    //게시판 생성 API
    @RequestMapping(method= RequestMethod.POST, value="/api/board")
    public BoardVO addBoard(@RequestBody BoardVO board){

        System.out.println("board: "+board);


    /*
    만일 lombok을 사용하지 않았다면 4바이트 메모리 주소값이 프린트 될것이다.
    하지만 lombok이 toString 메서드를 오버라이딩하여서
    board안에 있는 인스턴스 멤버값을 모두 출력해준다.
     */
      //  return new Result(0,"success");
        return board;
    }
}
