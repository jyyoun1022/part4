package part4.part4.repository;

import ch.qos.logback.core.spi.FilterReply;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import part4.part4.entity.Member;
import part4.part4.entity.Movie;
import part4.part4.entity.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {

        @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
        List<Review> findByMovie(Movie movie);

        @Modifying
        @Query("delete from Review mr where mr.member= :member")
        void deleteByMember(@Param("member") Member member);
}
