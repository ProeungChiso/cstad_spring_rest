package co.cstad.devops.rest.dto;

public record ProductEditRequest(
        String name,
        Double price
) {
}
