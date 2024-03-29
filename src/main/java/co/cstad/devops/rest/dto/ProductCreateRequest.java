package co.cstad.devops.rest.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductCreateRequest(
        @NotBlank
        String name,
        @NotNull
        @Positive
        Double price,
        @NotNull
        @Positive
        @Max(100)
        Integer qty
) {
}
