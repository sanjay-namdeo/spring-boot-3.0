package com.spring.boot.repository;

import com.spring.boot.entity.VideoEntity;
import org.springframework.data.repository.CrudRepository;

public interface VideoRepository extends CrudRepository<VideoEntity, Long> {
    VideoEntity findByNameIgnoreCase(String name);
}
