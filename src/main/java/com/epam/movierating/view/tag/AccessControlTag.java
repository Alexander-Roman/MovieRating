package com.epam.movierating.view.tag;

import com.epam.movierating.constant.Attribute;
import com.epam.movierating.entity.Account;
import com.epam.movierating.entity.Role;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TagSupport;

public class AccessControlTag extends TagSupport {

    private String accessName;

    public void setAccessName(String accessName) {
        this.accessName = accessName;
    }

    @Override
    public int doStartTag() {
        HttpSession session = pageContext.getSession();
        Account account = (Account) session.getAttribute(Attribute.ACCOUNT);
        if (account == null) {
            return SKIP_BODY;
        }

        Role role = account.getRole();
        //Access access = Access.valueOf(accessName);

        if (role.hasAccess(accessName)) {
            return EVAL_BODY_INCLUDE;
        } else {
            return SKIP_BODY;
        }
    }
}
