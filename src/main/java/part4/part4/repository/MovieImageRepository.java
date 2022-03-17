package part4.part4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import part4.part4.entity.MovieImage;

public interface MovieImageRepository extends JpaRepository<MovieImage,Long> {
}
