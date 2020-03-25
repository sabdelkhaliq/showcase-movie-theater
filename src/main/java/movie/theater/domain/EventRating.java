package movie.theater.domain;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author Yuriy_Tkach
 */
public enum EventRating {


    LOW(1),

    MID(2),

    HIGH(3);

    int value;

    EventRating(int value) {
        this.value = value;
    }

    public static Optional<EventRating> fromValue(int value) {
        return Arrays.stream(values())
                .filter(e -> e.value == value)
                .findFirst();
    }
}
