package uma.taw.ubayspring.wrapper;

import com.jlefebure.spring.boot.minio.MinioException;
import com.jlefebure.spring.boot.minio.MinioService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author Altair Bueno
 */
public class MinioWrapperService {
    private final MinioService minioService;

    public MinioWrapperService(MinioService minioService) {
        this.minioService = minioService;
    }


    public String uploadObject(InputStream inputStream) throws NoSuchAlgorithmException, IOException, MinioException {
        MessageDigest shaDigest = MessageDigest.getInstance("SHA-256");
        byte [] inputStreamContent = inputStream.readAllBytes();
        byte[] digest = shaDigest.digest(inputStreamContent);
        String objectName = Base64.getEncoder().encodeToString(digest);

        InputStream uploadStream = new ByteArrayInputStream(inputStreamContent);
        minioService.upload(Paths.get(objectName),uploadStream,"image/png");
        uploadStream.close();
        return objectName;
    }

    public InputStream getObject(String objectName) throws MinioException {
        if (objectName == null) return null;
        return minioService.get(Paths.get(objectName));
    }

    public void removeObject(String objectName) throws MinioException {
        if (objectName == null) return;
        minioService.remove(Paths.get(objectName));
    }

}
