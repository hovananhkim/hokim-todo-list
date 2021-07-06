package com.openwt.task.repositories;

import com.openwt.task.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByTitleContainsOrDetailContains(String title, String detail);

    @Query(value = "SELECT CASE WHEN COUNT(t)>0 THEN true ELSE false END FROM Task t where t.title=?1 AND t.detail=?2 AND t.status=false")
    boolean findTask(String title, String detail);
}
