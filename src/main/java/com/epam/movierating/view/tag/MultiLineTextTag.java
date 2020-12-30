package com.epam.movierating.view.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MultiLineTextTag extends TagSupport {

    private static final String LINE_PATTERN = ".+";
    private static final String HTML_LINE_BREAK = "<br>";
    private static final Pattern PATTERN = Pattern.compile(LINE_PATTERN);
    private String text;

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            writeWithLineBreaks();
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }

    private void writeWithLineBreaks() throws IOException {
        JspWriter writer = pageContext.getOut();

        Matcher matcher = PATTERN.matcher(text);
        if (matcher.find()) {
            String firstLine = matcher.group();
            writer.write(firstLine);

            while (matcher.find()) {
                String nextLine = matcher.group();
                writer.write(HTML_LINE_BREAK + nextLine);
            }
        }
    }
}
