package com.spring.boot.controller;

import com.spring.boot.record.Video;
import com.spring.boot.service.VideoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VideoController {
    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping(path = "/create-video")
    public ResponseEntity<Video> createVideo(@RequestBody Video video) {
        return ResponseEntity.ok().body(this.videoService.create(video));
    }

    @GetMapping(path = "/get-videos")
    public ResponseEntity<List<Video>> getVideos() {
        return ResponseEntity.ok().body(this.videoService.getVideos());
    }
}
