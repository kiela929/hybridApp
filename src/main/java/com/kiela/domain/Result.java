package com.kiela.domain;

import lombok.Data;

/**
 * Created by pino8 on 2016-09-12.
 */
@Data
public class Result {

    private int result;
    private String msg;

    public Result(int result, String msg) {
        this.result=result;
        this.msg = msg;
    }

/*    public Result(int result, String msg){
        this.result=result;
        this.msg=msg;

        System.out.println(result);
    }*/


}
