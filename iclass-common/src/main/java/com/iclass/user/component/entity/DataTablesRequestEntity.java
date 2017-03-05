package com.iclass.user.component.entity;

import java.io.Serializable;

/**
 * iclass
 * <p>
 * Created by JasonTang on 3/5/2017 10:32 PM.
 *
 * DataTables 请求实体
 * 接收的参数如下：
 * draw    绘制计数器。这个是用来确保Ajax从服务器返回的是对应的（Ajax是异步的，因此返回的顺序是不确定的）。
 *         要求在服务器接收到此参数后再返回（具体看 下面）
 * start   第一条数据的起始位置，比如0代表第一条数据
 * length  告诉服务器每页显示的条数，这个数字会等于返回的 data集合的记录数，可能会大于因为服务器可能没有那么多数据。这个也可能是-1，
 *         代表需要返回全部数据(尽管这个和服务器处理的理念有点违背)
 */
public class DataTablesRequestEntity implements Serializable{

    private Integer draw;

    private Integer start;

    private Integer length;

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "DataTablesRequestEntity{" +
                "draw=" + draw +
                ", start=" + start +
                ", length=" + length +
                '}';
    }
}
