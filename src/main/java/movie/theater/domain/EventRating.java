package movie.theater.domain;

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
}
