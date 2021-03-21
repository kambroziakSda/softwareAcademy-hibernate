package hibernate.core;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;
import java.time.LocalDateTime;

@MappedSuperclass
public class VersionedEntity {

    private LocalDateTime createTime;

    @Version
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
