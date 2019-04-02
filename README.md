# KafkaWebSockets
Kafka Web Sockets example for Gorilla Logic Blog Post.


## Prerequisites
Have kafka and zookeeper installed and running in your machine.

### Intellij prerequisites
Install the plugins and add the options for project Lombok `https://projectlombok.org/setup/intellij`


## Running the app
```
gradle clean bootRun
```

App will run locally in port `9091`.

For using websockets, connection should be done to `<app url>/messages/ws` and the web socket client should listen to `<app url>/queue/messages`.

For sending a message using endpoint POST request should be done to `<app url>/messages/message`.

Post example:
```
{
    "sender":"Test",
    "message":"Jello"
}
```

## UI Example
When running the application a UI example can be accessed in `<app url>/messages`. This will show an UI for sending and reading messages.
