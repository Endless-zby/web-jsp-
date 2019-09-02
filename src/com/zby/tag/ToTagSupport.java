package com.zby.tag;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.HashMap;

public class ToTagSupport extends BodyTagSupport {

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


    @Override
    public int doEndTag() throws JspException {
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put(1,"good");
        objectObjectHashMap.put(2,"77777");
        objectObjectHashMap.put(3,"88888");
        objectObjectHashMap.put(4,"55555");
        String NAME = getBodyContent().toString()+"--->"+objectObjectHashMap.get(value%4);
        try {
            bodyContent.getEnclosingWriter().write(NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.doEndTag();
    }
}
