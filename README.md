Part 1:

Q1) The default lifecycle of a JAX-RS Resource class has a new instance created for every incoming request. This means that resources are not shared between requests. However, in this application, we used shared in-memory data structures such as HashMaps. This caused me to manage the design more carefully since multiple requests can access the same data, which could very easily cause issues. 

Q2) HATEOAS is considered a hallmark because it allows API responses to link relevant sources throughout the code. These links can tell the client what can be done next, which makes the API much easier to use and understand. 


Part 2:

Q1) A full room object holds a lot more information than returning only the IDs. This will lead to higher transfer speeds and reduced performance, hence why it is better to return just the IDs. 

Q2) The first request would delete the room as intended. Every request after that will have the same outcome and will not change anything in the system. Therefore, DELETE is idempotent.


Part 3:

Q1) The @Consumes(MediaType.APPLICATION_JSON) annotations states that only a JSON input should be accepted by JAX-RS. If the client uses any other format, it will be rejected.

Q2) The query parameter approach is superior because multiple filters can be combined. This is quite useful when there there is more than one criteria that needs to be filtered.


Part 4:

Q1) Having every nested path in one controller path would be unnecessarily hard to maintaiin and keep clean. Delegating logic to separate classes keeps the system organised, efficient, and make it much easier to navigate through.


Part 5:

Q1) HTTP 422 is considered more accurate because 404 is used when a requested resource doesn't exist. However, 422 is used when the request is valid but it contains invalid data, such as a missing reference in this case. 

Q2) A risk associated with exposing internal stack traces is that the system details inside, such as class names and file paths, could very easily be exploited by attackers.

Q3) It is better to use filters because they make the code cleaner, which makes it easier to keep all the logging consistent.




Video demonstration link:
https://westminster.cloud.panopto.eu/Panopto/Pages/Viewer.aspx?id=5afd6f91-7cbe-4208-a829-b436007871dd
