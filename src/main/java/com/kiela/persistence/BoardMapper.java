package com.kiela.persistence;

import com.kiela.domain.AttachVO;
import com.kiela.domain.BoardVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by pino8 on 2016-10-11.
 */
@Mapper
public interface BoardMapper {

    @Insert("INSERT INTO board(board_id,user_id,title,content)VALUES(#{board_id},#{user_id},#{title},#{content})")
    void insertBoard(BoardVO board);

    @Select("select board.*,user.name from board inner join user on board.user_id=user.user_id where board_id=#{board_id}")
    BoardVO findById(@Param("board_id") int board_id);

    @Select("select board.*,user.name from board inner join user on board.user_id=user.user_id")
    List<BoardVO> findAll();

    @Insert("INSERT INTO attach(board_id, path, org_name, upd_name)VALUES(#{board_id}, #{path}, #{org_name}, #{upd_name})")
    void insertAttach(AttachVO attach);
}
