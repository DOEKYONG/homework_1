package homework.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Builder
public class BoardDto {
    private int bno;
    private String btitle;
    private String bcontent;
}
