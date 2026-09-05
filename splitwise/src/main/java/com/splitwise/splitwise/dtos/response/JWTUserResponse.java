package com.splitwise.splitwise.dtos.response;

public record JWTUserResponse(
        String id,
        String name,
        String email,
        String accessToken
) {

}
