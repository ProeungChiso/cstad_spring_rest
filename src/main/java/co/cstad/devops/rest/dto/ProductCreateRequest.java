package co.cstad.devops.rest.dto;

public record ProductCreateRequest(
        String name,
        Double price,
        Integer qty
) {
}
