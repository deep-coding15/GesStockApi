package com.deep_coding15.GesStockApi.common.exception;

import java.util.Map;

/**
 * throw new EntityIllegalArgumentException(
 * "Role",
 * "code",
 * role.getCode()
 * );
 */

public class EntityIllegalArgumentException extends BusinessException {

    public EntityIllegalArgumentException(String entity, String field, String value) {
        super(
                entity + ", illegal argument exception for: " + field + "=" + value,
                "ENTITY_ILLEGAL_ARGUMENT_EXCEPTION");
    }

    public EntityIllegalArgumentException(String entity, String field, String value, String commentaire) {
        super(
                entity + ", illegal argument exception for: " + field + "=" + value,
                "ENTITY_ILLEGAL_ARGUMENT_EXCEPTION" + " : " + commentaire);
    }
    
    public EntityIllegalArgumentException(String entity, String field, Long value) {
        super(
                entity + ", illegal argument exception for: " + field + "=" + value,
                "ENTITY_ILLEGAL_ARGUMENT_EXCEPTION");
    }
    
    public EntityIllegalArgumentException(String entity, String field, Long value, String commentaire) {
        super(
                entity + ", illegal argument exception for: " + field + "=" + value,
                "ENTITY_ILLEGAL_ARGUMENT_EXCEPTION" + " : " + commentaire);
    }

    /**
     * @param entity : entity in the system
     * @param data   : key => field, value => value
     */

    public EntityIllegalArgumentException(String entity, Map<String, String> data) {

        super(
                entity + ", illegal argument exception for: " + data.keySet() + "=" + data.values(),
                "ENTITY_ILLEGAL_ARGUMENT_EXCEPTION");

    }
}
