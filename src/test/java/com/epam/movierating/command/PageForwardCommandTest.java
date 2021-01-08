package com.epam.movierating.command;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;

public class PageForwardCommandTest {

    private static final String PAGE_PATH = "pagePath";
    private final PageForwardCommand pageForwardCommand = new PageForwardCommand(PAGE_PATH);
    private HttpServletRequest request = Mockito.mock(HttpServletRequest.class);

    @Test
    public void testExecuteShouldReturnCorrectCommandResult() {
        //given
        //when
        CommandResult actual = pageForwardCommand.execute(request);
        //then
        CommandResult expected = CommandResult.forward(PAGE_PATH);
        Assert.assertEquals(actual, expected);
    }
}
