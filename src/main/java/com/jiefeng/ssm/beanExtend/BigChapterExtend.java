package com.jiefeng.ssm.beanExtend;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BigChapterExtend implements Serializable {

    private Integer id;
    private String title;
    private String describe;
    private Date createTime;
    private int status;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getCreateTime() {
        return createTime;
    }
}
