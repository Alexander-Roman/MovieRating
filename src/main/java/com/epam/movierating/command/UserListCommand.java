package com.epam.movierating.command;

import com.epam.movierating.constant.Attribute;
import com.epam.movierating.constant.Page;
import com.epam.movierating.constant.Parameter;
import com.epam.movierating.model.entity.Account;
import com.epam.movierating.logic.AccountService;
import com.epam.movierating.logic.PageNotFoundException;
import com.epam.movierating.logic.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserListCommand implements Command {

    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_ITEMS_PER_PAGE = 8;
    private final AccountService accountService;

    public UserListCommand(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        String pageParameter = request.getParameter(Parameter.PAGE);
        int page;
        if (pageParameter == null) {
            page = DEFAULT_PAGE;
        } else {
            page = Integer.parseInt(pageParameter);
        }

        int numberOfPages = accountService.getNumberOfPages(DEFAULT_ITEMS_PER_PAGE);
        if (page > numberOfPages) {
            throw new PageNotFoundException();
        }

        List<Account> users = accountService.getPage(page, DEFAULT_ITEMS_PER_PAGE);

        request.setAttribute(Attribute.ITEMS_PER_PAGE, DEFAULT_ITEMS_PER_PAGE);
        request.setAttribute(Attribute.NUMBER_OF_PAGES, numberOfPages);
        request.setAttribute(Attribute.PAGE, page);
        request.setAttribute(Attribute.USERS, users);

        return CommandResult.forward(Page.USERS);
    }
}
