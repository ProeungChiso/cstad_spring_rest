package co.cstad.devops.rest.dto;

public record ProductResponse(
        String uuid,
        String name,
        Double price,
        Integer qty
) {
}
