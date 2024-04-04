# 1
>
    1. REST stands for Representational State Transfer and uses HTTP protocol (web protocol) for implementation.

    Every content in the REST architecture is considered a resource. The resource is analogous to the object in the object-oriented programming world. They can either be represented as text files, HTML pages, images, or any other dynamic data.
    
    Every resource is identified globally by means of a URI.


    These services are lightweight, provide maintainability, scalability, support communication among multiple applications that are developed using different programming languages.

# 2
>
    Uniform Resource Identifier is the full form of URI which is used for identifying each resource of the REST architecture.

    URN: Uniform Resource Name identifies the resource by means of a name that is both unique and persistent.
    //urn:isbn:1234567890 is used for identification of book based on the ISBN number in a library application.

    URL: Uniform Resource Locator has the information regarding fetching of a resource from its location. URLs start with a protocol (like ftp, http etc) and they have the information of the network hostname (sampleServer.com) and the path to the document(/samplePage.html). It can also have query parameters.
    //http://abc.com/samplePage.html
   
    scheme: 指底层用的协议，如http、https、ftp
    host: 服务器的IP地址或者域名
    port: 端口，http默认为80端口
    path: 访问资源的路径，就是各种web 框架中定义的route路由
    query: 查询字符串，为发送给服务器的参数，在这里更多发送数据分页、排序等参数。
    fragment: 锚点，定位到页面的资源
    //URI = scheme "://" host  ":"  port "/" path [ "?" query ][ "#" fragment ]

    path
    /{version}/{resources}/{resource_id}
    version：API版本号，有些版本号放置在头信息中也可以，通过控制版本号有利于应用迭代。
    resources：资源，RESTful API推荐用小写英文单词的复数形式。
    resource_id：资源的id，访问或操作该资源。



# 3
>
    HTTP Status codes
    1xx - represents informational responses
    2xx - represents successful responses
    3xx - represents redirects
    4xx - represents client errors
    5xx - represents server errors
    200 - success/OK
    201 - CREATED - used in POST or PUT methods.
    304 - NOT MODIFIED - used in conditional GET requests to reduce the bandwidth use of the network. Here, the body of the response sent should be empty.
    400 - BAD REQUEST - This can be due to validation errors or missing input data.
    401 - FORBIDDEN - sent when the user does not have access (or is forbidden) to the resource.
    404 - NOT FOUND - Resource method is not available.
    500 - INTERNAL SERVER ERROR - server threw some exceptions while running the method.
    502 - BAD GATEWAY - Server was not able to get the response from another upstream server.

    HTTP Methods
    POST, GET, PUT, DELETE corresponds to the create, read, update, delete operations which are most commonly called CRUD 
    GET: This is used for fetching details from the server and is basically a read-only operation.
    POST: This method is used for the creation of new resources on the server.
    PUT: This method is used to update the old/existing resource on the server or to replace the resource.
    DELETE: This method is used to delete the resource on the server.
    PATCH: This is used for modifying the resource on the server.
    OPTIONS: This fetches the list of supported options of resources present on the server.
    GET, HEAD, OPTIONS are safe and idempotent methods whereas PUT and DELETE methods are only idempotent. POST and PATCH methods are neither safe nor idempotent.
    idempotent：The result will not change not matter how many times same operations.
    safe: The operations will not change resouses.
    HTTP    Method	安全性	幂等性	解释
    GET	    安全	幂等	读操作安全，查询一次多次结果一致
    POST	非安全	非幂等	写操作非安全，每多插入一次都会出现新结果
    PUT	    非安全	幂等	写操作非安全，一次和多次更新结果一致
    DELETE	非安全	幂等	写操作非安全，一次和多次删除结果一致

    cookie
    单个Cookie保存的数据≤4KB，一个站点最多保存20个Cookie. Cookie 支持跨域名访问，例如，将 domain 属性设置为“.biaodianfu.com”，则以“.biaodianfu.
    场景
    （1）判断用户是否登陆过网站，以便下次登录时能够实现自动登录（或者记住密码）。如果我们删除cookie，则每次登录必须从新填写登录的相关信息。
    （2）保存上次登录的时间等信息。
    （3）保存上次查看的页面
    （4）浏览计数

    session
    Session的数据信息存放在服务器上。Session则不会支持跨域名访问。Session仅在它所在的域名内有效。
    场景
    （1）网上商城中的购物车
    （2）保存用户登录信息
    （3）将某些数据放入session中，供同一用户的不同页面使用
    （4）防止用户非法登录

    Token
    Token是服务端生成的一串字符串，以作客户端进行请求的一个令牌。当客户端第一次访问服务端，服务端会根据传过来的唯一标识userId，运用一些算法，并加上密钥，生成一个Token，然后通过BASE64编码一下之后将这个Token返回给客户端，客户端将Token保存起来（可以通过数据库或文件形式保存本地）。下次请求时，客户端只需要带上Token，服务器收到请求后，会用相同的算法和密钥去验证Token。
    客户端使用用户名跟密码请求登录
    服务端收到请求，去验证用户名与密码
    验证成功后，服务端会签发一个 Token，再把这个 Token 发送给客户端
    客户端收到 Token 以后可以把它存储起来，比如放在 Cookie 里或者数据库里
    客户端每次向服务端请求资源的时候需要带着服务端签发的 Token
    服务端收到请求，然后去验证客户端请求里面带着的 Token，如果验证成功，就向客户端返回请求的数据




