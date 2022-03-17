package part4.part4.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "movie")
public class MovieImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iNum;

    private String uuid;    //고유 번호

    private String imgName;

    private String path;    // '년/월/일' 폴더구조

    @ManyToOne(fetch=FetchType.LAZY)
    private Movie movie;
}
