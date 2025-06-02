package com.alura.litera_challenge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataAuthor(
        String name,
        int birth_year,
        int death_year
) {
}
