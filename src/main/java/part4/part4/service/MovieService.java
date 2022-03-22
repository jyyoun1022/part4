package part4.part4.service;

import part4.part4.dto.MovieDTO;
import part4.part4.dto.MovieImageDTO;
import part4.part4.dto.PageRequestDTO;
import part4.part4.dto.PageResultDTO;
import part4.part4.entity.Movie;
import part4.part4.entity.MovieImage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface MovieService {

    //등록처리
    Long register(MovieDTO movieDTO);
    //목록처리
    PageResultDTO<MovieDTO,Object[]> getList(PageRequestDTO pageRequestDTO);



    //DTO -> Entity 변환
    default Map<String,Object> dtoToEntity(MovieDTO movieDTO){  //Map타입으로 반환
        Map<String,Object> entityMap = new HashMap<>();

        Movie movie = Movie.builder()
                .mno(movieDTO.getMno())
                .title(movieDTO.getTitle())
                .build();
        entityMap.put("movie",movie);

        List<MovieImageDTO> imageDTOList = movieDTO.getImageDTOList();
        //MovieImageDTO 처리
        if(imageDTOList != null && imageDTOList.size() > 0){
            List<MovieImage> movieImageList = imageDTOList.stream().map(movieImageDTO -> {
                MovieImage movieImage = MovieImage.builder()
                        .path(movieImageDTO.getPath())
                        .imgName(movieImageDTO.getImgName())
                        .uuid(movieImageDTO.getUuid())
                        .movie(movie)
                        .build();
                return movieImage;
            }).collect(Collectors.toList());
            entityMap.put("imgList",movieImageList);
        }
        return entityMap;
    }
    //Entity -> DTO 변환
    default MovieDTO entityToDto(Movie movie,List<MovieImage>movieImages,Double avg,Long reviewCnt){
        MovieDTO movieDTO = MovieDTO.builder()
                .mno(movie.getMno())
                .title(movie.getTitle())
                .regDate(movie.getRegDate())
                .modDate(movie.getModDate())
                .build();

        List<MovieImageDTO>movieImageDTOList =movieImages.stream().map(movieImage -> {
            MovieImageDTO movieImageDTO = MovieImageDTO.builder()
                    .imgName(movieImage.getImgName())
                    .path(movieImage.getPath())
                    .uuid(movieImage.getUuid())
                    .build();
            return movieImageDTO;
        }).collect(Collectors.toList());

        movieDTO.setImageDTOList(movieImageDTOList);
        movieDTO.setAvg(avg);
        movieDTO.setReviewCnt(reviewCnt.intValue());

        return movieDTO;
    }
}
