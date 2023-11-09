package com.development.webcrawler;


import lombok.Getter;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Crawler")
public class Controller {


    @GetMapping("/test")
    public String test() {
        return "Server is running";
    }

    @PostMapping("/CrawlerStart")
    public String CrawlerStart(@RequestBody RequestBodyModel model) {

        try {
            WebCrawler crawler = new WebCrawler();
            crawler.crawl(model.rootPath , model.breakpoint);

            return "Done!";

        }catch (Exception e) {
            return "Error: " + e.getMessage();
        }


    }


}
