package com.spring.boot.service;

import com.spring.boot.entity.VideoEntity;
import com.spring.boot.record.Video;
import com.spring.boot.repository.VideoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VideoService {
    private final VideoRepository videoRepository;
    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public Video create(Video video) {
        VideoEntity videoEntity = new VideoEntity(video.name());
        VideoEntity saved = this.videoRepository.save(videoEntity);
        return new Video(saved.getId(), saved.getName());
    }

    public List<Video> getVideos() {
        List<Video> videoList = new ArrayList<>();

        Iterable<VideoEntity> all = this.videoRepository.findAll();
        all.forEach(videoEntity -> videoList.add(new Video(videoEntity.getId(), videoEntity.getName())));

        return videoList;
    }

    public Video findByName(String name) {
        VideoEntity byName = this.videoRepository.findByNameIgnoreCase(name);

        if (byName != null) {
            return new Video(byName.getId(), byName.getName());
        } else {
            return null;
        }
    }

    public Video findById(Long id) {
        Optional<VideoEntity> videoEntity = this.videoRepository.findById(id);
        if (videoEntity.isPresent()) {
            VideoEntity ve = videoEntity.get();
            return new Video(ve.getId(), ve.getName());
        } else {
            return null;
        }
    }
}
