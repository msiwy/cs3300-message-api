p1server
========

Web Server / REST API

# Specifications

## Authentication 

Authenticate a user

To authenticate a user, send a GET request to /auth with the parameter name set to your username. This request is responded by a unique int : userId. -1 is responded if the authentication fails, for example if parameter name is an empty String



### Request
```
GET /auth?name=<String : user_name>
```
| Request Parameter       | Type          | Description  |
| -------------   | ------------- | ------------ |
| name            | String	| User name being authenticated. |
### Response
```
{
	userId : 1234		// -1 for failure
}
```
| Response Variable        | Type          | Description  |
| -------------   | ------------- | ------------ |
| userId          | int	          | The GUID generated. Returns -1 on fail  |

## User

To request information regarding an existing user, send a GET request to /user with the parameter name set to your username. This request is responded by a unique int : userId, long : dateCreated, int[] : groups. 

### Request
```
GET /user?userName=<String : user_name>
```
| Request Parameter       | Type          | Description  |
| -------------   | ------------- | ------------ |
| userName            | String	  |  The User Name for the user you are fetching|
### Response
```
{
	userId : 1234,
	dateCreated :  1433453749290,	// In milliseconds
	groups : [1, 12, 56],		// Group IDs	
	????
}
```
| Response Parameter       | Type          | Description  |
| -------------   | ------------- | ------------ |
| userId            | int	  | The GUID for the user |
| dateCreated | long | The date the user was created in milliseconds
| groups | int[] | An array of groupId's the user belongs to |

Create a user
To create a user, send a POST request to /user as a JSON object containing a String : username. An int : userId and long : dateCreated will be returned. 
### Request
| Request Parameter | Type | Description  |
| ------------- | ------------- | ------------ |
| userName | String | Name of the user being created |
```
POST /user
{
	name : “Doug”
}
```
### Response
| Response Variable | Type | Description  |
| ------------- | ------------- | ------------ |
| userId | int | The GUID of the user |
| dateCreated | long | The date the user was created |
```
{
	userId : 2345,
	dateCreated : 1433453749290	// In milliseconds
}
```

## Group

Create a group
### Request
| Request Parameter | Type | Description  |
| ------------- | ------------- | ------------ |
| owner | String | The userName of the group creator |
| groupName | String | The desired name of the group |
```
POST /group
{
	owner : “Doug”,
	groupName : “Android Chatroom”
}
```
### Response
| Response Variable | Type | Description  |
| ------------- | ------------- | ------------ |
| success | boolean | Returns true if the group was created |
| groupId | int | Generated GUID for the group | 
```
{
	success : true,
	groupId : 56
}
```

Get a group
### Request
| Request Parameter | Type | Description  |
| ------------- | ------------- | ------------ |
| groupName | String | Name of the group being fetched |
```
GET /group?groupName=<group_name>
```
### Response
| Response Variables | Type | Description  |
| ------------- | ------------- | ------------ |
| groupId | int | The GUID of the group being fetched. -1 is returned if it does not exist   |
```
{
	groupId : 56,
	name : “Android Chatroom”,
	userIds : [1234, 1236, 2314, … ],
	messageIds : [123456, 546789, …. ],
	dateCreated : 1433453749290,
	owner : “Doug”

}
```


## Message
Send a message
To send a message, send a POST request to /message as a JSON object containing the parameters: String : from (username), String : to (user or chatroom name), and String : content. A successful request will be responded with boolean : success (true), a unique int : messageId, a unique int : groupId and a long : dateCreated. An unsuccessful request will be responded with a boolean : success (false) and an error object with a code and description of the error. 
### Request
```
POST /message
{
 	from: “Doug”,
	to: “John”,		// Chatroom names can also be passed 
	content: "Hey"
}

```
### Response
```
Success
{
	success : true,
	messageId : 345224,
	groupId : 56,		// A message to only 1 user will still have a groupId
	dateCreated : 1433453749290	// In milliseconds
}

Failure
{
	success : false
	error : {
		code : “1”,	// List of error codes will be created
		description : “User ‘George P. Burdell’ does not exist” 
	}
}
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
  messageId: 123456,
  contentType: "image/png",
  content: "...base 64 encoded..."
}
```

```
GET /document?id=<document_id>
```



