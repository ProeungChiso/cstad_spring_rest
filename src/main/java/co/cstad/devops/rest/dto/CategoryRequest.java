package co.cstad.devops.rest.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(
        @NotBlank
        String name,

        String description
) {

}
