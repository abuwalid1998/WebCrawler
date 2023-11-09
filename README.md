# WebCrawler


## API Reference

#### Test Server 

```http
  GET http://localhost:8080/Crawler/test
```

#### Input
```json
```

#### Output
```json
 Server is running
```
#### Train And Save Network

```http
  POST http://localhost:8080/Crawler/CrawlerStart
```
#### Input
```json

{
    "rootPath" : "https://www.w3schools.com/java/java_files_create.asp" ,
    "breakpoint" : 100
}

```

#### Output
```json

    Done!

```






## Note

 - Log file Will be Founded into project files , it has all visited sites by crawler from root Url. 

