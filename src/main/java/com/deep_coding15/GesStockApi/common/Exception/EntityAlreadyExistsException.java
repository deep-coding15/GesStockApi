package com.deep_coding15.GesStockApi.common.Exception;

public class EntityAlreadyExistsException extends BusinessException {

    /** 
     * throw new EntityAlreadyExistsException(
     *      "Role",
     *      "code",
     *      role.getCode()
        );
     */
    public EntityAlreadyExistsException(String entity, String field, String value) {
        super(
            entity + " already exists with " + field + "=" + value,
            "ENTITY_ALREADY_EXISTS"
        );
    }
}
