package com.intranet.dto.password.response;

public record FindPasswordResponse(
        boolean result,
        String tempPassword,
        String message
) {
}
