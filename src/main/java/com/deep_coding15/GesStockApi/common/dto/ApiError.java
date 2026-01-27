package com.deep_coding15.GesStockApi.common.dto;

import java.time.LocalDateTime;

public record ApiError(
    int status,
    String error,
    String message,
    String path,
    LocalDateTime timestamp
) {}

