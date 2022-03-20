package part4.part4.service;

import part4.part4.dto.MovieDTO;
import part4.part4.dto.MovieImageDTO;
import part4.part4.entity.Movie;
import part4.part4.entity.MovieImage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface MovieService {

    Long register(MovieDTO movieDTO);

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
            entityMap.put("imageList",movieImageList);
        }
        return entityMap;
    }
}