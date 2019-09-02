package com.zby.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class MyIteration extends TagSupport {
    private int num;
    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }
    //执行标签
    @Override
    public int doStartTag() throws JspException {
        return EVAL_BODY_INCLUDE;
    }
    //执行完毕后
    @Override
    public int doAfterBody() throws JspException {
        num-- ;
        return num>0 ? EVAL_BODY_AGAIN :SKIP_BODY; //重复执行
    }

}
