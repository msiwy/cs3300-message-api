p1server
========

Web Server / REST API



# Specifications

## Message

```
POST /message
{
  from: 1234,
  to: 1235,
  content: "Hey"
}
```

```
GET /message?id=<message_id>
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


