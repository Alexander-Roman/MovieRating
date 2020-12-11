package com.epam.movierating.view.tag;

import com.epam.movierating.constant.Attribute;
import com.epam.movierating.model.Role;
import com.epam.movierating.model.entity.Account;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.Objects;

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

        if (role.hasAccess(accessName)) {
            return EVAL_BODY_INCLUDE;
        } else {
            return SKIP_BODY;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AccessControlTag that = (AccessControlTag) o;
        return Objects.equals(accessName, that.accessName);
    }

    @Override
    public int hashCode() {
        return accessName != null ? accessName.hashCode() : 0;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "accessName='" + accessName + '\'' +
                '}';
    }
}
