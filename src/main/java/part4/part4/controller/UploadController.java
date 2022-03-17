package part4.part4.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

@RestController
@Log4j2
public class UploadController {

    @PostMapping("/uploadAjax")
    public void uploadFile(MultipartFile[] uploadFiles) {

////        for (MultipartFile uploadFile : uploadFiles) {
////
////            String originalName = uploadFile.getOriginalFilename();
////            String fileName = originalName.substring(originalName.lastIndexOf("\\")+1);
////
////
////            log.info("fileName: "+ fileName);
//
//
//        }
//    }

        Arrays.stream(uploadFiles).forEach(i->{
            String originalFilename = i.getOriginalFilename();
            String fileName = originalFilename.substring(originalFilename.lastIndexOf("\\") + 1);

            log.info(fileName);
        } );

        }
    }

