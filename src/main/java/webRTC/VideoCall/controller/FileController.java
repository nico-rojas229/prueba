package webRTC.VideoCall.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import webRTC.VideoCall.entity.UserEntity;
import webRTC.VideoCall.service.FileStorageService;
import webRTC.VideoCall.service.UserService;
import webRTC.VideoCall.user.Upload;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class FileController {
    
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    private final FileStorageService fileStorageService;

    public FileController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<Upload> uploadFile(
            @RequestParam(name = "file", required = false) MultipartFile file,
            @RequestParam("room") Integer roomId
    ) {

        if (Objects.requireNonNull(file.getOriginalFilename()).equalsIgnoreCase("")) {

            logger.error("Error file is empty");
            Upload uploadError = new Upload("null", "ERROR", "File is empty", null, 0);
            return ResponseEntity.ok().body(uploadError);

        }

        String message;
        String fileName = fileStorageService.storeFile(file, roomId);
        message = "File updated successfully";

        Upload uploadResponse = new Upload(fileName, "SUCCESS", message, roomId, file.getSize());

        logger.info("User send file in roomId: "+roomId);
        return ResponseEntity.ok().body(uploadResponse);

    }

}
