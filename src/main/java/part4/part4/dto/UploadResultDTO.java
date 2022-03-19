package part4.part4.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Data
@AllArgsConstructor
public class UploadResultDTO implements Serializable {
    //implements Serializable을 하는 이유: 자바 시스템 내부에서 사용되는 Object 또는 Data를
    // 외부의 자바 시스템에서도 사용할 수 있도록 바이트 형태로 데이터를 변환하는 기술로써,
    //JVM 메모리에 상주되어 있는 객체 데이터를 바이트 형태로 변환하는 기술입니다.(직렬화)

    private String fileName;

    private String uuid;

    private String folderPath;

    public String getImageURL(){    //추후에 전체 경로가 필요한 경우를 대비하여 생성
        try{
            return URLEncoder.encode(folderPath+"/"+uuid+"_"+fileName,"UTF-8");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return "";
    }
}
