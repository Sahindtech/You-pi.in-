package com.youpi.youpi.controller;

import com.youpi.youpi.entity.Banners;
import com.youpi.youpi.service.BannersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/content/banners")
public class BannersController {

    @Autowired
    private BannersService bannerService;

    @GetMapping
    public List<Banners> getAllBanners() {
        return bannerService.getAllBanners();
    }

    @PostMapping
    public Banners createBanner(@RequestBody Banners banners) {
        return bannerService.createBanner(banners);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBanner(@PathVariable Long id) {
        bannerService.deleteBanner(id);
        return ResponseEntity.noContent().build();
    }
}