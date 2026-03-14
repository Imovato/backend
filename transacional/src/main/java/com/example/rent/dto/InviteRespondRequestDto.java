package com.example.rent.dto;

import com.example.rent.enums.InviteStatus;
import jakarta.validation.constraints.NotNull;

public record InviteRespondRequestDto(@NotNull InviteStatus action) {
}

