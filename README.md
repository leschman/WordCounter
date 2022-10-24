# Word Counter
Word Counter is a Spring Boot app that receives messages,
stores them in its database and will return the total number of words received thus far. The app leverages Kotlin, Jackson, Feign, H2 and Flyway.

## Set up
* Use Java 17
* `$./gradlew bootRun` to run migrations and start the application
* default port is `:9316`

## Usage
The application has one endpoint, `/message`. It accepts POST requests with a JSON body containing an `id` and a `message`. 
Messages with duplicated IDs are ignored but logged. A `Count` is returned of the total number of words received so far by the app.
Message text is stripped of non-alphanumeric characters prior to counting words.

### Example curl request
```
curl -X POST localhost:9316/message \
-H 'Content-Type: application/json' \
-d '{"id":"123","message":"hello world"}'
```

### Sample response
```
{"count":2}
```

### Data reset
`rm -rf service/data`