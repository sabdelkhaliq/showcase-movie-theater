package movie.theater.domain;

/**
 * @author Yuriy_Tkach
 */
public class DomainObject {

    private static Long count = 0L;

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DomainObject() {
        this.id = count;
        count++;
    }
}
