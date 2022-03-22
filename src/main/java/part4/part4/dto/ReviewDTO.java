package part4.part4.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReviewDTO {

    private Long reviewNum;
    //Movie mno
    private Long mno;
    //Member id
    private Long mid;
    //Member nickName
    private String nickName;
    //Member email
    private String email;

    private int grade;

    private String text;

    private LocalDateTime regDate,modDate;
}
