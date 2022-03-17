package part4.part4.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import part4.part4.entity.Movie;
import part4.part4.entity.MovieImage;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
public class MovieRepositoryTests {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieImageRepository movieImageRepository;

    @Test
    @Transactional
    @Commit
    void insertMovies(){

        IntStream.rangeClosed(1,100).forEach(i->{

            Movie movie = Movie.builder().title("Movie..." + i).build();

            System.out.println("======================================================");

            movieRepository.save(movie);

            int count = (int) (Math.random() * 5) + 1;

            for(int j =0; j< count; j++){
                MovieImage movieImage = MovieImage.builder()
                        .uuid(UUID.randomUUID().toString())
                        .movie(movie)
                        .imgName("test" + j + ".jpg")
                        .build();

                movieImageRepository.save(movieImage);
            }
            System.out.println("======================================================");

        });
    }

    @Test
    void testListPage(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());

        Page<Object[]> result = movieRepository.getListPage(pageable);

        result.getContent().forEach(i-> System.out.println(Arrays.toString(i)));
    }

    @Test
    void testGetMovieWithAll(){

        List<Object[]> result = movieRepository.getMovieWithAll(92L);

        System.out.println(result);

        result.forEach(i-> System.out.println(Arrays.toString(i)));
    }
}
