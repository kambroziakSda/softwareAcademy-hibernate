package hibernate.core;

import javax.persistence.PrePersist;
import java.time.LocalDateTime;

public class VersionedEntity {

    private LocalDateTime createTime;

    private LocalDateTime lastModifiedTime;

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public LocalDateTime getLastModifiedTime() {
        return lastModifiedTime;
    }

    @PrePersist
    public void prePersist(){
        createTime = LocalDateTime.now();
    }
}
