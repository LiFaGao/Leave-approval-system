package pojo;

import java.util.List;

public class Page<T> {
    private int totalPagenum;//总页数
    private int totalnumber;//总记录数
    private int currentPage;
    private int row;
    private List<T> list;

    public int getTotalPagenum() {
        return totalPagenum;
    }

    public void setTotalPagenum(int totalPagenum) {
        this.totalPagenum = totalPagenum;
    }

    public int getTotalnumber() {
        return totalnumber;
    }

    public void setTotalnumber(int totalnumber) {
        this.totalnumber = totalnumber;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Page{" +
                "totalPagenum=" + totalPagenum +
                ", totalnumber=" + totalnumber +
                ", currentPage=" + currentPage +
                ", row=" + row +
                ", list=" + list +
                '}';
    }
}
