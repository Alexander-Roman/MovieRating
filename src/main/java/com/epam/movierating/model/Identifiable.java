package com.epam.movierating.model;

import java.io.Serializable;

/**
 * A marker interface for limiting the scope of processed entities in DAO layers
 */
public interface Identifiable extends Serializable {

    /**
     * Entities must have IDs to be supported in the abstract DAO model
     * @return ID value of specific entity
     */
    Long getId();
}
