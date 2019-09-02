package com.zby.tag;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class LoginOut extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException, IOException {
        PageContext jspContext = (PageContext) getJspContext();
        HttpSession session = jspContext.getSession();
        String username = (String) session.getAttribute("username");
        if (username == null) {
            getJspBody().invoke(null);
        }
    }
}
