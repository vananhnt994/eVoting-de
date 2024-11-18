package org.evoting.de.infrastructure.repositories;

import org.evoting.de.domain.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    @Query("SELECT t FROM Topic t " +
            "JOIN t.topicLists tl " +
            "WHERE tl.cityName = :cityName")
    List<Topic> findByCityName(@Param("cityName") String cityName);
}