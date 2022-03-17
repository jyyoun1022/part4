package part4.part4.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import part4.part4.entity.Member;
import part4.part4.entity.Movie;
import part4.part4.entity.Review;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class ReviewRepositoryTests {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    void insertReview(){

        IntStream.rangeClosed(1,200).forEach(i->{
            //영화번호
             long mno = (long)(Math.random() * 100) + 1;
            //리뷰어 번호
             long mid = ((long)(Math.random() * 100) + 1);

            Member member = Member.builder().mid(mid).build();

            Review review =Review.builder()
                    .movie(Movie.builder().mno(mno).build())
                    .member(member)
                    .grade((int)(Math.random()*5)+1)
                    .text("이 영화에 대한 느낌..."+i)
                    .build();

            reviewRepository.save(review);

        });
    }

    @Test
    void testGetMovieReview(){

        Movie movie = Movie.builder().mno(11L).build();

        List<Review> result = reviewRepository.findByMovie(movie);

        result.forEach(movieReview ->{
            System.out.print(movieReview.getReviewNum());
            System.out.print("\t" + movieReview.getGrade());
            System.out.print("\t" + movieReview.getText());
            System.out.print("\t" + movieReview.getMember().getEmail());
            System.out.println("===================================");
        });
    }

}
