package io.github.joyoungc.web.controller;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@RestController
@Slf4j
public class FileUploadController {
	
    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "D://github//spring-boot-sample-project//spring-boot-web//src//main//resources//static//";
	
	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile uploadFile) {
		log.info("Uploaded file name is ==> {}", uploadFile.getOriginalFilename());
		
		if (uploadFile.isEmpty()) {
            return new ResponseEntity("please select a file!", HttpStatus.OK);
        }
		
		try {
			
			// File destinationDir = new File(UPLOADED_FOLDER + "");
			
			Thumbnails.of(uploadFile.getInputStream())
	        .size(200, 200)
	        .toFile(UPLOADED_FOLDER + "thumbnail.jpg");
			
			
			LocalDateTime.no
			
/*			 Files.copy(file.getInputStream(), this.rootLocation.resolve(filename),
	                    StandardCopyOption.REPLACE_EXISTING);*/
			
/*			Thumbnails.of(uploadFile.getInputStream())
			.size(160, 160)
			.toFile(UPLOADED_FOLDER + "thumbnail.jpg");*/
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity("Successfully uploaded - " +
				uploadFile.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);
		
	}

}
