package part4.part4.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import part4.part4.dto.ReviewDTO;
import part4.part4.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@Log4j2
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{mno}/all")
    public ResponseEntity<List<ReviewDTO>>getList(@PathVariable("mno")Long mno){

        log.info("-----------------------lis-----------------------");
        log.info("mno: "+mno);
        List<ReviewDTO> reviewDTOList = reviewService.getListOfMovie(mno);

        return new ResponseEntity<>(reviewDTOList, HttpStatus.OK);
    }
    @PostMapping("/{mno}")
    public ResponseEntity<Long> addReview (@RequestBody ReviewDTO reviewDTO){
        log.info("<-----------------------add Review----------------------->");
        log.info("reviewDTO : "+reviewDTO);

        Long reviewNum = reviewService.register(reviewDTO);

        return new ResponseEntity<>(reviewNum,HttpStatus.OK);
    }
    @PutMapping("/{mno}/{reviewNum}")
    public ResponseEntity<Long>modifyReview (@PathVariable("mno")Long reviewNum,
                                             @RequestBody ReviewDTO reviewDTO){
        log.info("<-----------------------modify removeReview----------------------->");
        log.info("reviewNum : "+reviewNum);

        reviewService.remove(reviewNum);

        return new ResponseEntity<>(reviewNum,HttpStatus.OK);
    }
}
