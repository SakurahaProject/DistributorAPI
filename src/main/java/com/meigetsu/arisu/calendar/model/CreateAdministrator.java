package com.meigetsu.arisu.calendar.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.ALWAYS)
public record CreateAdministrator(
        @JsonProperty("email") String mailAddress,
        @JsonProperty("password") String password) {
}
