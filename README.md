# WebClient-vs-RestTemplate

# RestTemplate is Synchronous in behaviour
#the code will block waiting for the response from the service. Only when the response has been received, the rest of the code in this method will be executed.
Output for http://localhost:8080/getWithResttemplate endpoint:
Starting Controller!1625331576424
before1625331576452
after1625331578456
com.uday.model.Employee@65816999
com.uday.model.Employee@6c249f0
com.uday.model.Employee@1d96c0b3
Exiting BLOCKING Controller!1625331578470
#It executes sequentially

# WebClient is Asynchronous in behaviour
#WebClient returns a Flux/Mono publisher and the method execution gets completed. Once the result is available, the publisher will start objects/object to its subscribers.
Output for http://localhost:8080/getEmployeesWithWebclient endpoint:
Starting NON-BLOCKING Controller!
Exiting NON-BLOCKING Controller!
before1625331761239
before1625331761240
after1625331763252
after1625331763252
com.uday.model.Employee@175f8654
com.uday.model.Employee@24fc285a
com.uday.model.Employee@1c44ee98
#It executes asynchronously


you can pass requestbody using syncBody 
Eg:
webClient.post().uri("/uri").syncBody(request).retrieve().bodyToMono(String.class);
