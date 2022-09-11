package br.com.helpme.helpmecore.user.model;

import java.util.Arrays;
import java.util.Optional;

public enum RoleName {
    ROLE_ADMIN,
    ROLE_USER;

    public String getRoleWithoutPrefix(){

        Optional<RoleName> roleOptional = Arrays.stream(values())
                .filter(roleNameFilter -> roleNameFilter.equals(this))
                .findAny();

        String[] roleSeparetedByPrefix = roleOptional.get().name().split("_");

        return roleSeparetedByPrefix[1];

    }
}
