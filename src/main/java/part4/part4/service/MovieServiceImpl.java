package part4.part4.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import part4.part4.dto.MovieDTO;
import part4.part4.dto.PageRequestDTO;
import part4.part4.dto.PageResultDTO;
import part4.part4.entity.Movie;
import part4.part4.entity.MovieImage;
import part4.part4.repository.MovieImageRepository;
import part4.part4.repository.MovieRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepository;
    private final MovieImageRepository movieImageRepository;

    @Override
    @Transactional
    public Long register(MovieDTO movieDTO) {

        Map<String,Object> entityMap = dtoToEntity(movieDTO);
        Movie movie = (Movie) entityMap.get("movie");
        List<MovieImage> movieImageList =(List<MovieImage>) entityMap.get("imgList");
        //Map에서 꺼내온 값들을 Movie,List<MovieImage>로 형변환

        movieRepository.save(movie);

        movieImageList.forEach(movieImage -> {
            movieImageRepository.save(movieImage);
        });

        return movie.getMno();
    }

    @Override
    public PageResultDTO<MovieDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {

        Pageable pageable = pageRequestDTO.getPageable(Sort.by("mno").descending());

        Page<Object[]> result = movieRepository.getListPage(pageable);
        Function<Object[],MovieDTO> fn = (arr ->entityToDto(
                (Movie)arr[0],
                (List<MovieImage>)(Arrays.asList((MovieImage)arr[1])),
                (Double)arr[2],
                (Long) arr[3]));


        return new PageResultDTO<>(result,fn);

    }

    @Override
    public MovieDTO getMovie(Long mno) {

        List<Object[]> result = movieRepository.getMovieWithAll(mno);
        Movie movie =(Movie)result.get(0)[0];
        //Movie 엔티티는 가장 앞에 존재(모든 Row가 동일한 값을 가집니다.)

        List<MovieImage>movieImageList = new ArrayList<>();
        //영화의 이미지 개수 만큼 MovieImage객체 필요

        result.forEach(arr ->{
            MovieImage movieImage = (MovieImage)arr[1];
            movieImageList.add(movieImage);
        });

        Double avg = (Double) result.get(0)[2];
        //평균 평점 - 모든 Row가 동일한 값
        Long reviewCnt = (Long)result.get(0)[3];
        //리뷰 개수 - 모든 Row가 동일한 값

        return entityToDto(movie,movieImageList,avg,reviewCnt);
    }
}
