package com.spring.boot.service;

import com.spring.boot.entity.VideoEntity;
import com.spring.boot.record.Video;
import com.spring.boot.repository.VideoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
