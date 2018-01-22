//: com.yuli.d3lab.controllers.IndexController.java


package com.yuli.d3lab.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Slf4j
@Controller
public class IndexController {

    static final String HTTP_HEADER_CONTENT_TYPE_CSV = "text/csv";
    static final String HTTP_HEADER_CONTENT_TYPE_JSON = "application/json";

    static final String PATH_CITIES_CSV = "/static/data/cities.csv";
    static final String PATH_TWEETS_JSON = "/static/data/tweets.json";

    @RequestMapping({"", "/", "/home"})
    public String getIndexPage() {
        return "index";
    }

    @GetMapping
    @RequestMapping({"/d3lab/{chapter}/{pageName}"})
    public String getContentPage(@PathVariable String chapter,
                                 @PathVariable String pageName) {
        return chapter + "/" + pageName;
    }

    @GetMapping
    @RequestMapping("/api/data/cities")
    public void getCitiesCsvFile(HttpServletResponse response) {
        File file = new File(getClass().getResource(PATH_CITIES_CSV).getFile());
        this.responseFile(response, file, HTTP_HEADER_CONTENT_TYPE_CSV);
    }

    @GetMapping
    @RequestMapping("/api/data/tweets")
    public void getTweetsJsonFile(HttpServletResponse response) {
        File file = new File(getClass().getResource(PATH_TWEETS_JSON).getFile());
        this.responseFile(response, file, HTTP_HEADER_CONTENT_TYPE_JSON);
    }

    private void responseFile(HttpServletResponse response, File file,
                              String contentType) {

        assert response != null : "No HttpServletResponse!";
        assert file != null : "No file to send!";
        assert contentType != null : "No content type specified!";

        try (InputStream inputStream = new FileInputStream(file)) {
            response.setContentType(contentType);
            response.setHeader("Content-Disposition",
                    "attachment; filename=" + file.getName());
            response.setHeader("Content-Length",
                    String.valueOf(file.length()));
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

}///:~