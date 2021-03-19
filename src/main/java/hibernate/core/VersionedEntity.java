package hibernate.core;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@MappedSuperclass
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
