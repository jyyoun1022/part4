package part4.part4.service;

import part4.part4.dto.ReviewDTO;
import part4.part4.entity.Member;
import part4.part4.entity.Movie;
import part4.part4.entity.Review;

import java.util.List;

public interface ReviewService {

    //영화의 모든 리뷰를 가져옵니다
    List<ReviewDTO> getListOfMovie(Long mno);
    //영화에 리뷰를 추가합니다.
    Long register(ReviewDTO movieReviewDTO);
    //특정한 영화의 리뷰를 수정합니다.
    void modify(ReviewDTO movieReviewDTO);
    //특정한 영화의 리뷰를 삭제합니다.
    void remove(Long reviewNum);

    default Review dtoToEntity(ReviewDTO reviewDTO){

        Review review = Review.builder()
                .reviewNum(reviewDTO.getReviewNum())
                .movie(Movie.builder().mno(reviewDTO.getMno()).build())
                .member(Member.builder().mid(reviewDTO.getMid()).build())
                .grade(reviewDTO.getGrade())
                .text(reviewDTO.getText())
                .build();

        return review;
    }
    default ReviewDTO entityToDto(Review review){

        ReviewDTO reviewDTO = ReviewDTO.builder()
                .reviewNum(review.getReviewNum())
                .mno(review.getMovie().getMno())
                .mid(review.getMember().getMid())
                .nickName(review.getMember().getNickName())
                .email(review.getMember().getEmail())
                .grade(review.getGrade())
                .text(review.getText())
                .regDate(review.getRegDate())
                .modDate(review.getModDate())
                .build();

        return reviewDTO;
    }
}
