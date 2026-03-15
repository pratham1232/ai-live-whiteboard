package com.whiteboard.repository;
import com.whiteboard.model.Stroke;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StrokeRepository extends JpaRepository<Stroke, Long> {
    // MUST ADD THIS to return points in correct order
    List<Stroke> findAllByOrderByIdAsc();
}