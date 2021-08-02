package com.example.local_image_controller_final_project_backend.service;

import antlr.StringUtils;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageStorageService {
    /**
     * ImageStorageService responsible for CRUD operations image and thumbnail files stored locally
     */

    private static String currentFileName;

    public static String getCurrentFileName() {
        return currentFileName;
    }

    public static void setCurrentFileName(String currentFileName) {
        ImageStorageService.currentFileName = currentFileName;
    }

    /**
     * saveImageToLocalStorage method coverts uploaded image into bytes and saves it at chosen path + name
     * returns String path to that image. CurrentFileName is checked with existing files and regenerated
     */

    public String saveImageToLocalStorageAndGetPath(MultipartFile imageFile, String imagesStoragePath) throws Exception {

        setCurrentFileName(generateImageFileName(imagesStoragePath, imageFile.getOriginalFilename()));

        byte[] imageBytes = imageFile.getBytes();
        Path imagePath = Paths.get(imagesStoragePath + getCurrentFileName());
        Files.write(imagePath, imageBytes);

        System.out.println("Current file name: " + imagesStoragePath + getCurrentFileName());

        return imagesStoragePath + getCurrentFileName();
    }

    /**
     * Method checks originalImageFileName with existing files in localStorage by given imageStoragePath.
     * If any match exist - originalImageFileName is edited by adding counter before .jpg ending.
     */

    private String generateImageFileName(String imageStoragePath, String originalImageFileName) throws IOException {

        int counter = 0;
        List<String> allImagesList = getAllImagesList(imageStoragePath);
        boolean matchFound = getAnyMatchInStorage(allImagesList, originalImageFileName);

        while (matchFound) {
            counter++;
            //removing all symbols like / or . and etc. from string and adding .jpg ending:
            originalImageFileName = originalImageFileName.replaceAll("\\W+","");
            String editedImageFileName = StringUtils.stripBack(originalImageFileName, "jpg") + counter + ".jpg";
            if (!getAnyMatchInStorage(allImagesList, editedImageFileName)) {
                return editedImageFileName;
            }
        }
        return originalImageFileName;
    }

    private boolean getAnyMatchInStorage(List<String> list, String originalFileName) throws IOException {
        return list.stream().anyMatch(e -> e.endsWith(originalFileName));
    }

    public List<String> getAllImagesList(String imageStoragePath) throws IOException {
        Path path = Paths.get(imageStoragePath);
        return Files.walk(path).filter(Files::isRegularFile).map(Path::toString).collect(Collectors.toList());
    }

    /**
     * createThumbnailImage method uses Thumblinator (Thumbnails.of()) to convert uploaded image file into small size thumbnail
     * returns String path to that thumbnail
     */

    public String createThumbnailImageAndGetPath(String imagesStoragePath, String thumbnailStoragePath) throws Exception {
        File newThumbnailFile = new File(thumbnailStoragePath);

        Thumbnails.of(String.format("%s/%s", imagesStoragePath, getCurrentFileName())).size(250, 250).toFiles(newThumbnailFile, Rename.PREFIX_HYPHEN_THUMBNAIL);
        String thumbnailFileLocation = thumbnailStoragePath + "/thumbnail-" + getCurrentFileName();

        System.out.println("Created new thumbnail file at: " + thumbnailFileLocation);

        return thumbnailFileLocation;
    }

    public void deleteImageAndThumbnailFromStorage(Path imagePath, Path thumbnailPath) throws IOException {
        Files.deleteIfExists(imagePath);
        Files.deleteIfExists(thumbnailPath);
        System.out.printf("Successfully remove image at %s and thumbnail at %s \n", imagePath, thumbnailPath);
    }

//    public File getImageToDownload(String imageFilePath) throws ImageFileToDownloadNotFound {
//       try{
//           File imageToDownload = new File(imageFilePath);
//           if (imageToDownload.exists()){
//               return imageToDownload;
//           }else {
//               throw new ImageFileToDownloadNotFound("Couldn't find file at " + imageFilePath);
//           }
//       }catch (Exception e){
//           throw new ImageFileToDownloadNotFound("Couldn't find file at " + imageFilePath, e);
//       }
//    }
}
