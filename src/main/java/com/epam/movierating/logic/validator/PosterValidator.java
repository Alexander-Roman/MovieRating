package com.epam.movierating.logic.validator;

import javax.servlet.http.Part;

public class PosterValidator implements Validator<Part> {

    private static final int MAX_POSTER_SIZE = 1024 * 1024; //1MB
    private static final String JPEG = "image/jpeg";
    private static final String PNG = "image/png";

    @Override
    public boolean isValid(Part posterPart) {
        if (posterPart == null) {
            return false;
        }
        long size = posterPart.getSize();
        if (size == 0 || size > MAX_POSTER_SIZE) {
            return false;
        }
        String contentType = posterPart.getContentType();
        return JPEG.equals(contentType) || PNG.equals(contentType);
    }
}
