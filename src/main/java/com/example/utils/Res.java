package com.example.utils;

import lombok.Data;

@Data
public class Res {
    private Boolean flag;
    private Object data;

    private String msg;

    public Res() {
    }

    public Res(Boolean flag) {
        this.flag = flag;
    }

    public Res(String msg){
        this.msg=msg;
    }

    public Res(Boolean flag, Object data) {
        this.flag = flag;
        this.data = data;
    }

    public Res(Boolean flag, String msg) {
        this.flag = flag;
        this.msg  = msg;
    }

    public Res(Boolean flag,Object data, String msg ) {
        this.flag = flag;
        this.data = data;
        this.msg  = msg;
    }
}
