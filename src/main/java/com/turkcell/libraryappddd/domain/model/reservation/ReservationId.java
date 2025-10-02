package com.turkcell.libraryappddd.domain.model.reservation;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public record ReservationId(UUID value) implements Serializable {
    public ReservationId{
        Objects.requireNonNull(value, "value is null");
    }

    public static ReservationId generate(){
        return new ReservationId(UUID.randomUUID());
    }


}
