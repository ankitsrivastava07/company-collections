package com.company.collections.dto.error;

import java.util.List;

public record ErrorResponse(
        String code,
        String message,
        long timestamp,
        List<ErrorDetail> details // Reference directly inside the file
) {
    // Nested record definition
    public record ErrorDetail(String target, String reason) {}
}
