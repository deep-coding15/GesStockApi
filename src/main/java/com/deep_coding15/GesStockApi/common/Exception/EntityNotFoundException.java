package com.deep_coding15.GesStockApi.common.Exception;

public class EntityNotFoundException extends BusinessException {

    /** 
     * throw new EntityNotFoundException("Role", code);
     */
    public EntityNotFoundException(String entity, String identifier, String value) {
        this(entity, identifier, value, null);
    }

    public EntityNotFoundException(String entity, String identifier, String value, String message) {
        super(
            entity + " not found with identifier: " + identifier + " with value: " + value + " message: " + message,
            "ENTITY_NOT_FOUND"
        );
    }
}
