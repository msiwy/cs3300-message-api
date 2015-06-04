p1server
========

Web Server / REST API



# Specifications

## Authentication 

To authenticate a user, send a GET request to /auth with the parameter id set to your username. 
### Request
```
GET /auth?id=<String : user_name>
```
### Response
```
{
userId : 1234		// -1 for failure
}
```

## User

### Request
```
GET /user?id=<user_id>
```
### Response
```
{
	userName : “Doug”
	dateCreated :  1433453749290	// In milliseconds
groups : [1, 12, 56]		// Group IDs	
????
}
```
### Request
```


## Message

### Request
```
POST /message
{
  from: 1234,
  to: 1235,
  content: "Hey"
}
```
### Response
```
{}
```


### Request
```
GET /message?id=<message_id>
```
### Response
```
{}
```



## Document

```
POST /document
{
  messageId: 123,
  contentType: "image/png",
  content: "......base 64 encoded..."
}
```

```
GET /document?id=<document_id>
```



