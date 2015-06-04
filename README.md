p1server
========

Web Server / REST API



# Specifications

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


