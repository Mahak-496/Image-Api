package com.example.imageApi.repository;

import com.example.imageApi.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface ImageRepository extends JpaRepository<Image,Integer> {



    Optional<Image> findByfileName(String imageName);
}
