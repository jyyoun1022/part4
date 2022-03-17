package part4.part4.entity;


import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"movie","member"})
@Entity
public class Review extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewNum;

    @ManyToOne(fetch=FetchType.LAZY)
    private Movie movie;

    @ManyToOne(fetch=FetchType.LAZY)
    private Member member;

    private int grade;

    private String text;

}
