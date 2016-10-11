package com.kiela.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by pino8 on 2016-09-12.
 */
@Data
public class BoardVO {
    private int board_id;
    private int user_id;
    private String title;
    private String content;
    private String created;
    private String updated;

    private List<MultipartFile> files;

    //board 와 user를 조인하여 사용자 이름을 받아와서 매핑할 때 필요.
    //만일 이 값이 numm이면 json매핑때 빠지게 된다.
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
}
