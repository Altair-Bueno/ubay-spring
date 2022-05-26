package uma.taw.ubayspring.controller;

import com.jlefebure.spring.boot.minio.MinioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import uma.taw.ubayspring.wrapper.MinioWrapperService;

import java.io.IOException;

/**
 * @author Altair Bueno
 */
@RequestMapping("/image")
@RestController
public class ImageController {
    @Autowired
    MinioWrapperService minioWrapperService;

    @GetMapping(produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam String id) throws MinioException, IOException {
        var stream = minioWrapperService.getObject(id);
        return stream.readAllBytes();
    }
}
