package com.teedao.rbac.param;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Created by Zhang on 2016-9-11.
 * jquery.datatable 接受参数超类
 */
public class DataTableParam implements Serializable {
    private static final long serialVersionUID = -401706500806595490L;
    private Integer length;
    private Integer start;
    private Integer draw=0;

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
