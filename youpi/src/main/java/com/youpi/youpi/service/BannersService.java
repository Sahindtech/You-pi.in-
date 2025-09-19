package com.youpi.youpi.service;

import com.youpi.youpi.entity.Banners;
import com.youpi.youpi.repository.BannersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannersService {

    @Autowired
    private BannersRepository bannerRepository;

    public List<Banners> getAllBanners() {
        return bannerRepository.findAll();
    }

    public Banners createBanner(Banners banner) {
        return bannerRepository.save(banner);
    }

    public void deleteBanner(Long id) {
        bannerRepository.deleteById(id);
    }

    // Yahan update ke liye bhi method add kar sakte hain
}