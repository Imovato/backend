package com.example.rent.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
@Accessors(fluent = true)
@Getter
public class PropertyDTO {

    private final Long id;
    private final @NonNull Integer amount;
}