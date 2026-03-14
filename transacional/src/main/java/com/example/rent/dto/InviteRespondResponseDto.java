package com.example.rent.dto;

import com.example.rent.enums.InviteStatus;

public record InviteRespondResponseDto(String inviteId, InviteStatus status) {
}

