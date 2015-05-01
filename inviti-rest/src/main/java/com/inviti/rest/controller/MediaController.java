package com.inviti.rest.controller;

import com.inviti.service.mediaservice.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@RestController
@RequestMapping("/media")
@Secured("USER")
public class MediaController {
    @Autowired
    private MediaService mediaService;


    @RequestMapping(value="/upload", method=RequestMethod.GET)
    public @ResponseBody String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }


    @RequestMapping(value="/upload", method= RequestMethod.POST)
    public @ResponseBody
    String handleFileUpload(/*@RequestParam("name") String name,*/ @RequestParam/*@RequestPart/*("file")*/ MultipartFile file){
        String name = null;
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(name)));
                stream.write(bytes);
                stream.close();
                return "success";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }
}
