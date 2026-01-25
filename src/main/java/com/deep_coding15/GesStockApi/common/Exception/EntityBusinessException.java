package com.deep_coding15.GesStockApi.common.Exception;

/**
 * throw new EntityIllegalArgumentException(
 * "Role",
 * "code",
 * role.getCode()
 * );
 */

public class EntityBusinessException extends BusinessException {
    public EntityBusinessException(String entity, String field, String value, String commentaire) {
        super(
                entity + ", illegal argument exception for: " + field + "=" + value,
                "ENTITY_BUSINESS_EXCEPTION" + " : " + commentaire);
    }
}

  