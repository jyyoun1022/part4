package part4.part4.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import part4.part4.dto.ReviewDTO;
import part4.part4.entity.Movie;
import part4.part4.entity.Review;
import part4.part4.repository.ReviewRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;


    @Override
    public List<ReviewDTO> getListOfMovie(Long mno) {
        Movie movie = Movie.builder().mno(mno).build();

        List<Review> result = reviewRepository.findByMovie(movie);
        return result.stream().map(movieReview ->entityToDto(movieReview)).collect(Collectors.toList());
    }

    @Override
    public Long register(ReviewDTO movieReviewDTO) {
        Review review = dtoToEntity(movieReviewDTO);
        Review result = reviewRepository.save(review);
        return result.getReviewNum();
    }

    @Override
    public void modify(ReviewDTO movieReviewDTO) {
        Optional<Review> result = reviewRepository.findById(movieReviewDTO.getReviewNum());

        if(result.isPresent()){
            Review review = result.get();
            review.changeGrade(movieReviewDTO.getGrade());
            review.changeText(movieReviewDTO.getText());
        }
    }

    @Override
    public void remove(Long reviewNum) {
        reviewRepository.deleteById(reviewNum);

    }
}
