# intro-rest

> REST is an architectural style, or design pattern, for APIs.

## Introduction and overview


![Image](https://github.com/tvanwinckel/image.jpg?raw=true "image")

The goal of this document is to offer an easy to understand overview of what REST is. How it came to be, how it is intended to be used and how you can design your own REST-API

* REST: the theory
  * Before there was REST
  * Where does REST come from?
  * ...
* REST with Spring
  * ...

---

## REST: the theory

### Before there was REST

TODO

### Where does REST come from?

REST was defined by Roy Fielding, a computer scientist. He presented the REST principles in his PhD dissertation in 2000. 

REST stands for **RE**presentational **S**tate **T**ransfer. This means when a RESTful API is called, the server will transfer to the client a representation of the state of the requested resource. 

It is an architecture style for designing loosely coupled applications over the network, that is often used in the development of web services. REST does not enforce any rule regarding how it should be implemented at the lower level, it just put high-level design guidelines and leaves us to think of our own implementation.

### Explaining the rules

Before we dive into what makes an API RESTful and what constraints and rules you should follow if you want to create RESTful APIs, let’s explain two key terms:

* **Client**: the client is the person or software who uses the API. It can be a developer, for example you, as a developer, can use an API to read and write data from Twitter/X, create a new tweet and do more actions in a program that you write. Your program will call Twitter’s API. The client can also be a web browser. When you go to Twitter website, your browser is the client who calls Twitter API and uses the returned data to render information on the screen.
* **Resource**: a resource can be any object the API can provide information about. In Instagram’s API, for example, a resource can be a user, a photo, a hashtag. Each resource has a unique identifier. The identifier can be a name or a number.

A RESTful web application exposes information about itself in the form of information about its resources. It also enables the client to take actions on those resources, such as create new resources (i.e. create a new user) or change existing resources (i.e. edit a post).

In order for your APIs to be RESTful, you have to follow a set of (architectural) constraints when you write them. The REST set of constraints will make your APIs easier to use and also easier to discover, meaning a developer who is just starting to use your APIs will have an easier time learning how to do so. These constraints are as follow:

* Uniform interface
* Client <-> server separation
* Stateless
* Layered system
* Cacheable
* Code-on-demand

#### Uniform Interface:

The following four guidelines can help us achieve a uniform REST interface:

* **Identification of resources**: The interface must uniquely identify each resource involved in the interaction between the client and the server.
* **Manipulation of resources through representations**: The resources should have uniform representations in the server response. API consumers should use these representations to modify the resources state in the server.
* **Self-descriptive messages**: Each resource representation should carry enough information to describe how to process the message. It should also provide information of the additional actions that the client can perform on the resource.
* **Hypermedia as the engine of application state**: The client should have only the initial URI of the application. The client application should dynamically drive all other resources and interactions with the use of hyperlinks.

> Can we put in an example piece of JSON showing this?

#### Client <-> Server separation:

The client-server design pattern enforces the separation of concerns, which helps the client and the server components evolve independently. By separating the user interface concerns (client) from the data storage concerns (server), we improve the portability of the user interface across multiple platforms and improve scalability by simplifying the server components.

While the client and the server evolve, we have to make sure that the interface/contract between the client and the server does not break.

Also, the client and the server act independently, each on its own, and the interaction between them is only in the form of requests, initiated by the client only. Responses, which the server send to the client are a reaction to a request. The server just sits there waiting for requests from the client to come. The server doesn’t start sending away information about the state of some resources on its own.

#### Stateless:

Statelessness mandates that each request from the client to the server must contain all of the information necessary to understand and complete the request. The server does not remember anything about the user who uses the API after it responded to a request.

The server cannot take advantage of any previously stored context information on the server. For this reason, the client application must entirely keep the session state.

#### Layered System:

``The layered system style allows an architecture to be composed of hierarchical layers by constraining component behavior.``

``For example, in a layered system, each component cannot see beyond the immediate layer they are interacting with.``

``Between the client who requests a representation of a resource’s state, and the server who sends the response back, there might be a number of servers in the middle. These servers might provide a security layer, a caching layer, a load-balancing layer, or other functionality. Those layers should not affect the request or the response. The client is agnostic as to how many layers, if any, there are between the client and the actual server responding to the request.``

#### Cacheable:

The cacheable constraint requires that a response should implicitly or explicitly label itself as cacheable or non-cacheable. If the response is cacheable, the client application gets the right to reuse the response data later for equivalent requests and a specified period.

#### Code-on-demand:

This final constraint is **optional**. REST also allows client functionality to extend by downloading and executing code in the form of applets or scripts. The downloaded code simplifies clients by reducing the number of features required to be pre-implemented. Servers can provide part of features delivered to the client in the form of code, and the client only needs to execute the code.

### Zooming in on a Resource

The key **abstraction of information** in REST is a resource. Any information that we can name can be a resource. For example, a REST resource can be a document or image, a temporal service, a collection of other resources, or a non-virtual object (e.g., a person). The **state of the resource**, at any particular time, is known as the **resource representation**.

A REST API consists of an assembly of interlinked resources. This set of resources is known as the REST API’s resource model.

The resource representation should consist of:

* **Data**
* **Metadata**, describing the data
* **Hypermedialinks**, that can help in transition to the next state.

#### Identifiers:

REST uses resource identifiers to identify each resource involved in the interactions between the client and the server components.

#### Hypermedia:

The data format of a representation is known as a **media type**. The media type identifies a specification that defines how a representation is to be processed. A RESTful API looks like hypertext. Every addressable unit of information carries an address, either explicitly (e.g., link and id attributes) or implicitly (e.g., derived from the media type definition and representation structure).

> Hypertext (or hypermedia) means the **simultaneous presentation of information and controls** such that the information becomes the affordance through which the user (or automaton) obtains choices and selects actions. 
>
>Remember that **hypertext does not need to be HTML (or XML or JSON) on a browser**. Machines can follow links when they understand the data format and relationship types.
>
>— Roy Fielding

#### Self-Descriptive:

Further, **resource representations shall be self-descriptive**: the client does not need to know if a resource is an employee or a device. It should act based on the media type associated with the resource. So in practice, we will create lots of custom media types, usually one media type associated with one resource.

Every media type defines a default processing model. For example, HTML defines a rendering process for hypertext and the browser behavior around each element.

### Resource Methods

Another important thing associated with REST is **resource methods**. These resource methods are used to perform the desired transition between two states of any resource. There have never been any recommendations in the original REST proposal around which method to use in which condition. All that is emphasized is that is should be a **uniform interface**.

An example: A large number of people wrongly relate resource methods to HTTP methods (i.e., GET/PUT/POST/DELETE). if we decide that the application APIs will use HTTP POST for updating a resource – rather than most people recommend HTTP PUT – it’s all right. Still, the application interface will be RESTful.

Ideally, everything needed to transition the resource state shall be part of the resource representation – including all the supported methods and what form they will leave the representation.

> We should enter a REST API with no prior knowledge beyond the initial URI (a bookmark) and a set of standardized media types appropriate for the intended audience (i.e., expected to be understood by any client that might use the API).
>
>From that point on, all application state transitions must be driven by the client selection of server-provided choices present in the received representations or implied by the user’s manipulation of those representations.

## REST over HTTP

Though REST also intends to make the web (internet) more streamlined and standard. The REST standard has nowhere mentioned any implementation direction, including any protocol preference or even HTTP. Despite that, many people prefer to compare and implement REST using the HTTP protocol but they are not the same. 

### Identifying a resource

When implementing REST using HTTP, one can use the URL as a unique identifier for a resource.

```http
http://www.my-rest-api.com/api/resource-type/1
```

The above example 

### Media Types

using headers

### Resource Methods

https://restfulapi.net/http-methods/

| Method    | Usage     |
| :------  | -------  |
| GET       | Used to retrieve a resource. |
| HEAD      | Used to retrieve headers only. |
| OPTIONS   | Used to retrieve documentation. |
| PUT       | Used to create a new resource and an ID will be returned OR update and existing resource. |
| PATCH     | Used to update one or more properties of a resource. |
| DELETE    | Used to delete a resource. |
| POST      | Used to create a resource, no ID will be returned. |

#### GET

Use GET requests to retrieve resource representation/information only – and not modify it in any way. As GET requests do not change the resource’s state, these are said to be safe methods.Additionally, GET APIs should be idempotent. Making multiple identical requests must produce the same result every time until another API (POST or PUT) has changed the state of the resource on the server.

##### Response codes:

* For any given HTTP GET API, if the resource is found on the server, then it must return HTTP response code **200 (OK)** along with the response body, which is usually either XML or JSON (defined by the response header) content (due to their platform-independent nature).
* In case the resource is NOT found on the server then API must return HTTP response code **404 (NOT FOUND)**.
* Similarly, if it is determined that the GET request itself is not correctly formed then the server will return the HTTP response code **400 (BAD REQUEST)**.

#### Examples:
```http
GET-all of a specific resource-type:
    http://www.my-rest-api.com/api/resource-type/

GET-one of a specific resource-type by id:
    http://www.my-rest-api.com/api/resource-type/1

GET-property of a specific resource:
    http://www.my-rest-api.com/api/resource-type/1/property
```

#### PUT

Use PUT APIs primarily to **update an existing resource (if the resource does not exist, then API may decide to create a new resource or not)**. If the request passes through a cache and the Request-URI identifies one or more currently cached entities, those entries SHOULD be treated as stale. Responses to PUT method are **not cacheable**.

##### Response codes:

* If a new resource has been created by the PUT API, the origin server MUST inform the user agent via the HTTP response code **201 (Created)** response.
* If an existing resource is modified, either the **200 (OK)** or **204 (No Content)** response codes SHOULD be sent to indicate successful completion of the request.

#### Examples:
```http
PUT-addition of a resource-type:
    http://www.my-rest-api.com/api/resource-type/

PUT-addition of a resource-type's property:
    http://www.my-rest-api.com/api/resource-type/1/property/456
```

> The difference between the POST and PUT APIs can be observed in request URIs. POST requests are made on resource collections, whereas PUT requests are made on a single resource.

#### PATCH

HTTP PATCH requests are to make a **partial update on a resource**. If you see PUT requests modify a resource entity too. So to make it more precise – the PATCH method is the correct choice for partially updating an existing resource, and you should only use PUT if you’re replacing a resource in its entirety.

#### DELETE

As the name applies, DELETE APIs **delete the resources** (identified by the Request-URI). DELETE operations are idempotent. If you DELETE a resource, it’s removed from the collection of resources. Some may argue that it makes the DELETE method non-idempotent. It’s a matter of discussion and personal opinion.

If the request passes through a cache and the Request-URI identifies one or more currently cached entities, those entries SHOULD be treated as stale. Responses to this method are **not cacheable**.

##### Response codes:

* A successful response of DELETE requests SHOULD be an HTTP response code **200 (OK)** if the response includes an entity describing the status.
* The status should be **202 (Accepted)** if the action has been queued.
* The status should be **204 (No Content)** if the action has been performed but the response does not include an entity.
* Repeatedly calling DELETE API on that resource will not change the outcome – however, calling DELETE on a resource a second time will return a **404 (NOT FOUND)** since it was already removed.

##### Example:
```http
DELETE-of a specific resource-type by id:
    http://www.my-rest-api.com/api/resource-type/1

DELETE-of a resource-type's property:
    http://www.my-rest-api.com/api/resource-type/1/property/456
```

#### POST

Use POST APIs to create new subordinate resources, e.g., a file is subordinate to a directory containing it or a row is subordinate to a database table. When talking strictly about REST, POST methods are used to create a new resource into the collection of resources. Responses to this method are not cacheable unless the response includes appropriate Cache-Control or Expires header fields. Please note that POST is neither safe nor idempotent, and invoking two identical POST requests will result in two different resources containing the same information (except resource ids).

##### Response codes:

* Ideally, if a resource has been created on the origin server, the response SHOULD be HTTP response code **201 (Created)** and contain an entity that describes the status of the request and refers to the new resource, and a Location header.
* Many times, the action performed by the POST method might not result in a resource that can be identified by a URI. In this case, either HTTP response code **200 (OK)** or **204 (No Content)** is the appropriate response status.

Examples:
```http
POST-action on a specific resource-type by id:
    http://www.my-rest-api.com/api/resource-type/1

POST-action on a property of a specific resource:
    http://www.my-rest-api.com/api/resource-type/1/property
```


#### Status Codes

In the above exmples we talk about the commonly used response status codes that are used for specific HTTP methods. In practice there are a whole lot more status codes available that can provide a more fine-grained / detailed description to the end user or application using your API.

The status codes are divided into five main categories:

| Status code range     | Usage          | Simple Words |
| :--------------:      | -----------    | -------------|
|  1XX                  | Informational  | Hold on...   |
|  2XX                  | Successful     | Here you go! |
|  3XX                  | Redirection    | Go Away      |
|  4XX                  | Client Error   | You screwed up |
| 5XX                   | Server Error   | I screwed up |

A complete list of status codes can be found [here](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status "Status codes").

#### Query parameters

For certain HTTP methods we can enrich our request by using additional parameters. They are a defined set of parameters attached to the end of a URL. They are an extensions of the URL and are used to help define specific content or actions based on the data being passed.

To append query params to the end of a URL, a *?* is added followed immediately by a parameter.To add multiple parameters, an *&* is added between each.

```http
http://www.my-rest-api.com/api?search=keyword

http://www.my-rest-api.com/api/resource?limit=5&type=property-type
```

### Layered system

For an example of a layered system we can take a look at Cross-Origin Resource sharing. It is an HTTP-header based mechanism that allows a server to indicate any origins (domain, scheme, or port) other than its own from which a browser should permit loading resources. CORS also relies on a mechanism by which browsers make a "preflight" request to the server hosting the cross-origin resource, in order to check that the server will permit the actual request. In that preflight, the browser sends headers that indicate the HTTP method and headers that will be used in the actual request.

An example of a cross-origin request: the front-end JavaScript code served from https://domain-a.com uses XMLHttpRequest to make a request for https://domain-b.com/data.json.

For security reasons, browsers restrict cross-origin HTTP requests initiated from scripts. For example, XMLHttpRequest and the Fetch API follow the same-origin policy. This means that a web application using those APIs can only request resources from the same origin the application was loaded from unless the response from other origins includes the right CORS headers.

![CORS](https://github.com/tvanwinckel/image.jpg?raw=true "CORS")

More information about CORS can be found [here](https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS "CORS")


### Caching

https://restfulapi.net/caching/

Earlier when we stated the constraints of REST, caching was one of them. It stated that a resource should label itself as cacheable or non-cacheable.

This however can easily be done by adding an E-Tag to a response coming from the server. An E-Tag can be seen as a digital fingerprint of your response. Every time the client performs the same request, he can ask if the fingerprint is changes. And instead of the server sending the full response back to the client, the server can respond with a *304-Not modified* response code. Which tells the client nothing has changed and he can used his own caches response from the previous request.

An example:
```http
GET 
> http://www.my-rest-api.com/api/resource

RESPONSE
< HTTP/2 200 
< Cache-Control: max-age=0, must-revalidate
< Content-Length: 524653
< ETag: "ebeb4dbc1362d124452335a71286c21d"

GET 
> http://www.my-rest-api.com/api/resource
> If-None-Match: "ebeb4dbc1362d124452335a71286c21d"

RESPONSE
< HTTP/2 304 
```

Additionally the sever can add an *Expires* or *Cach-Control* header to the response. The first header defines an absolute expiry time for the caches resources. Beyond that time, a cached representation is considered stale and must be re-validated with the origin server. The second header defines if a response is cachable and for how long.

```http
Expires: Fri, 20 May 2016 19:20:49 GMT

Cache-Control: max-age=3600
```

More details on how to use Cache-Control can be found [here](https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Cache-Control)

### Versioning

Change in an API is inevitable as our knowledge and experience of a system improve. Managing the impact of this change can be quite a challenge when it threatens to break existing client integration.

#### When to version?

APIs only need to be up-versioned when a breaking change is made. Breaking changes include:

* A change in the format of the response data for one or more calls
* A change in the request or response type (i.e. changing an integer to a float)
* removing any part of the API.

#### Versioning strategies:

REST doesn’t provide for any specific versioning guidelines, but the more commonly used approaches fall into three categories:

##### URI versioning:
Using the URI is the most straightforward approach (and most commonly used as well) though it does violate the principle that a URI should refer to a unique resource. You are also guaranteed to break client integration when a version is updated.

```txt
http://www.my-rest-api.com/api/v1/resource

http://www.v1.my-rest-api.com/api/resource
```

##### Custom header versioning:

A custom header (e.g. Accept-version) allows you to preserve your URIs between versions though it is effectively a duplicate of the content negotiation behavior implemented by the existing Accept header.

```txt
Accept-version: v1
Accept-version: v2
```

##### Accept header versioning

Content negotiation may let you preserve a clean set of URLs, but you still have to deal with the complexity of serving different versions of content somewhere. This burden tends to be moved up the stack to your API controllers which become responsible for figuring out which version of a resource to send.

The result tends to be a more complex API as clients have to know which headers to specify before requesting a resource.

```txt
Accept: application/version.my-rest-api+json
Accept: application/version.my-rest-api+json;version=1.0
```

---

## REST with Spring

In this part of the course we are going to create a simple CRUD application, using REST to create, update and delete resources. A lot of concepts to implement REST have been handles in the intro-spring-web session earlier.

### A quick recap

#### Controllers

Controllers can be seen as the entry point for requests into your application. We can annotate controllers with the ``@Controller`` annotation. This is simply a specialization of the ``@Component`` class, which allows us to auto-detect implementation classes through the classpath scanning.

We typically use **@Controller in combination with a @RequestMapping** annotation for request handling methods.

```java
@Controller
@RequestMapping("books")
public class SimpleBookController {

    @GetMapping("/{id}", produces = "application/json")
    public @ResponseBody Book getBook(@PathVariable int id) {
        return findBookById(id);
    }

    private Book findBookById(int id) {
        // ...
    }
}
```

``@RestController`` is a specialized version of the controller. It **includes the @Controller and @ResponseBody** annotations, and as a result, simplifies the controller implementation.

```java
@RestController
@RequestMapping("books-rest")
public class SimpleBookRestController {
    
    @GetMapping("/{id}", produces = "application/json")
    public Book getBook(@PathVariable int id) {
        return findBookById(id);
    }

    private Book findBookById(int id) {
        // ...
    }
}
```

#### RequestBody & ResponseBody

Simply put, the ``@RequestBody`` annotation maps the HttpRequest body to a transfer or domain object, enabling automatic deserialization of the inbound HttpRequest body onto a Java object. Spring automatically deserializes the JSON into a Java type, assuming an appropriate one is specified. By default, the type we annotate with the @RequestBody annotation must correspond to the JSON sent from our client-side controller.

```java
@PostMapping("/request")
public ResponseEntity postController(
  @RequestBody LoginForm loginForm) {
 
    exampleService.fakeAuthenticate(loginForm);
    return ResponseEntity.ok(HttpStatus.OK);
}
```

```java
public class LoginForm {
    private String username;
    private String password;
    // ...
}
```

The ``@ResponseBody`` annotation tells a controller that the object returned is automatically serialized into JSON and passed back into the HttpResponse object.

```java
public class ResponseTransfer {
    private String text; 
    
    // standard getters/setters
}
```

```java
@Controller
@RequestMapping("/post")
public class ExamplePostController {

    @Autowired
    ExampleService exampleService;

    @PostMapping("/response")
    @ResponseBody
    public ResponseTransfer postResponseController(
      @RequestBody LoginForm loginForm) {
        return new ResponseTransfer("Thanks For Posting!!!");
     }
}
```

#### RequestMapping

You can use the @RequestMapping annotation to map requests to controllers methods. It has various attributes to match by URL, HTTP method, request parameters, headers, and media types. You can use it at the class level to express shared mappings or at the method level to narrow down to a specific endpoint mapping.

There are also HTTP method specific shortcut variants of @RequestMapping:

* @GetMapping
* @PostMapping
* @PutMapping
* @DeleteMapping
* @PatchMapping

```java
@Controller
@RequestMapping("/persons")
class PersonController {

 @GetMapping
 public Person getPersons() {
  // ...
 }

 @PostMapping
 public void add(@RequestBody Person person) {
  // ...
 }
}
```

#### Pathvariables

The ``@PathVariable`` annotation can be used to handle template variables in the request URI mapping, and set them as method parameters.

```java
@GetMapping("/api/employees/{id}")
@ResponseBody
public String getEmployeesById(@PathVariable String id) {
    return "ID: " + id;
}
```

#### Requestparameters

We can use ``@RequestParam`` to extract query parameters, form parameters, and even files from the request.

```java
@GetMapping("/api/employees")
@ResponseBody
public String getEmployeesById(@RequestParam String id) {
    return "ID: " + id;
}
```

#### ResponseStatus

If we want to specify the response status of a controller method, we can mark that method with ``@ResponseStatus``. Also note, that Spring only uses @ResponseStatus, when the marked method completes successfully (without throwing an Exception).

```java
@GetMapping("/api/employees")
@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
public String getEmployeesById(@PathVariable String id) {
    return "ID: " + id;
}
```

Note: you can use ``@ResponseStatus`` on errorhandlers, or exception classes as well. Therefore it would be made possible to attach the correct response status to a handled exception.

```java
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
class CustomException extends RuntimeException {}
```

#### ResponseEntity

``ResponseEntity`` represents the whole HTTP response: status code, headers, and body. As a result, we can use it to fully configure the HTTP response. If we want to use it, we have to return it from the endpoint; Spring takes care of the rest. ResponseEntity is a generic type. Consequently, we can use any type as the response body.

```java
@GetMapping("/customHeader")
ResponseEntity<String> customHeader() {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Custom-Header", "foo");
        
    return new ResponseEntity<>(
      "String body", headers, HttpStatus.OK);
}
```

#### RequestHeaders

If we need access to a specific header, we can configure ``@RequestHeader`` with the header name.

```java
@GetMapping("/greeting")
public ResponseEntity<String> greeting(@RequestHeader(HttpHeaders.ACCEPT_LANGUAGE) String language) {
    // code that uses the language variable
    return new ResponseEntity<String>(greeting, HttpStatus.OK);
}
```

If we're not sure which headers will be present, or we need more of them than we want in our method's signature, we can use the @RequestHeader annotation without a specific name. We can get our headers as an HttpHeaders object.

```java
@GetMapping("/getBaseUrl")
public ResponseEntity<String> getBaseUrl(@RequestHeader HttpHeaders headers) {
    InetSocketAddress host = headers.getHost();
    String url = "http://" + host.getHostName() + ":" + host.getPort();
    return new ResponseEntity<String>(String.format("Base URL = %s", url), HttpStatus.OK);
}
```

### Creating a REST API (server)

**TODO**

## Schemas

# Sources

* https://www.ics.uci.edu/~fielding/pubs/dissertation/top.htm
* https://www.d.umn.edu/~gshute/softeng/principles.html