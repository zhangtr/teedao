package com.teedao.rbac.result;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * jquery.datatable 控件封装数据
 * Created by Zhang on 2016-8-28.
 */
public class DataTable<E> implements Serializable {
    private static final long serialVersionUID = 4012548989850202718L;

    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private List<E> data;

    public DataTable() {
        super();
    }

    public DataTable(int draw, int count, List<E> data) {
        this.draw = draw;
        this.recordsTotal = count;
        this.recordsFiltered = count;
        this.data = data;
    }

    public DataTable(int draw, int recordsTotal, int recordsFiltered, List<E> data) {
        this.draw = draw;
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsFiltered;
        this.data = data;
    }



    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<E> getData() {
        return data;
    }

    public void setData(List<E> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
