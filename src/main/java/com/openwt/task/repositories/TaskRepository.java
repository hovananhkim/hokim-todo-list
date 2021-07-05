package com.openwt.task.repositories;

import com.openwt.task.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Query(
            value = "SELECT t FROM Task t where t.title LIKE CONCAT('%',:key ,'%') OR t.detail LIKE CONCAT('%',:key ,'%')"
    )
    Iterable<Task> findByTitleAndDetailContaining(@Param("key") String key);
}
