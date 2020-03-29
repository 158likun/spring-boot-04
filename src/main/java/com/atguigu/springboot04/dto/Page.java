package com.atguigu.springboot04.dto;

import com.atguigu.springboot04.bean.Goods;
import org.apache.xerces.xs.datatypes.ObjectList;

import java.util.List;

        import java.util.ArrayList;
        import java.util.List;

public class Page {
    private List<Goods> goods;
    private boolean previous;//alt+center//
    private boolean firstpage;
    private boolean endpage;
    private  boolean next;
    private Integer page;
    private List<Integer> pages=new ArrayList<Integer>();
    private Integer totalpage;
    public void setPageInation(Integer totalcount,Integer page,Integer size){
        Integer totalpage=0;
        if(totalcount%size==0)
        {
            totalpage=totalcount/size;
        }
        else
        {
            totalpage=(totalcount/size)+1;
        }
        this.totalpage=totalpage;
        if(page<1)
        {
            page=1;
        }
        else
        {
            if(page>totalpage)
            {
                page=totalpage;
            }
        }
        this.page=page;
        //pages列表展示7个值
        if(totalpage<=7)
        {
            for(int i=1;i<=totalpage;i++)
            {
                pages.add(i);
            }
            //全部列上
        }
        else
        {
            if(page<=4)
            {
                for(int i=1;i<=7;i++)
                {
                    pages.add(i);
                }
                //1<-page->7，
            }
            else
            {
                if(page<=(totalpage-3))
                {
                    for(int i=page-3;i<=page+3;i++)
                    {
                        pages.add(i);
                    }
                    //(page-3)<-page->(page+3)
                }
                else
                {
                    for(int i=totalpage-6;i<=totalpage;i++)
                    {
                        pages.add(i);
                    }
                    //(page-3)<-page->totalpage
                }
            }
        }
        //是否展示上一页
        if(page==1)
        {
            previous=false;
        }
        else
        {
            previous=true;
        }
        //是否展示下一页
        if(page==totalpage)
        {
            next=false;
        }
        else
        {
            next=true;
        }
        //是否展示首页
        if(pages.contains(1))
        {
            firstpage=false;
        }
        else
        {
            firstpage=true;
        }
        //是否展示尾页
        if(pages.contains(totalpage))
        {
            endpage=false;
        }
        else
        {
            endpage=true;
        }

    }

    public List<Goods> getGoods() {
        return goods;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    public boolean isPrevious() {
        return previous;
    }

    public void setPrevious(boolean previous) {
        this.previous = previous;
    }

    public boolean isFirstpage() {
        return firstpage;
    }

    public void setFirstpage(boolean firstpage) {
        this.firstpage = firstpage;
    }

    public boolean isEndpage() {
        return endpage;
    }

    public void setEndpage(boolean endpage) {
        this.endpage = endpage;
    }

    public boolean isNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }
    public List<Integer> getPages(){
        return pages;
    }
    public void setPages(List<Integer> pages) {
        this.pages = pages;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(Integer totalpage) {
        this.totalpage = totalpage;
    }
}
