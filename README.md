p1server
========

Web Server / REST API

# Specifications
## Authentication 
### Authenticate a user
To authenticate a user, send a GET request to /auth with the parameter name set to your username. This request is responded by a unique int : userId. -1 is responded if the authentication fails, for example if parameter name is an empty String
#### Request
| Request Parameter       | Type          | Description  |
| -------------   | ------------- | ------------ |
| name            | String	| User name being authenticated. |
```
GET /auth?name=<String : user_name>
```
#### Response
| Response Variable        | Type          | Description  |
| -------------   | ------------- | ------------ |
| userId          | int	          | The GUID generated. Returns -1 on fail  |
```
{
	userId : 1234		// -1 for failure
}
```
## User
### Get user info
To request information regarding an existing user, send a GET request to /user with the parameter name set to your username. This request is responded by a unique int : userId, long : dateCreated, int[] : groups. 
#### Request
| Request Parameter       | Type          | Description  |
| -------------   | ------------- | ------------ |
| userName            | String	  |  The User Name for the user you are fetching|
```
GET /user?userName=<String : user_name>
```
#### Response
| Response Parameter       | Type          | Description  |
| -------------   | ------------- | ------------ |
| userId            | int	  | The GUID for the user |
| dateCreated | long | The date the user was created in milliseconds
| groups | int[] | An array of groupId's the user belongs to |
```
{
	userId : 1234,
	dateCreated :  1433453749290,	// In milliseconds
	groups : [1, 12, 56],		// Group IDs	
	????
}
```
### Create a user
To create a user, send a POST request to /user as a JSON object containing a String : username. An int : userId and long : dateCreated will be returned. 
#### Request
| Request Parameter | Type | Description  |
| ------------- | ------------- | ------------ |
| userName | String | Name of the user being created |
```
POST /user
{
	name : “Doug”
}
```
#### Response
| Response Variable | Type | Description  |
| ------------- | ------------- | ------------ |
| userId | int | The GUID of the user |
| dateCreated | long | The date the user was created in milliseconds |
```
{
	userId : 2345,
	dateCreated : 1433453749290	// In milliseconds
}
```
## Group
### Create a group
#### Request
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
#### Response
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
### Get a group's info
#### Request
| Request Parameter | Type | Description  |
| ------------- | ------------- | ------------ |
| groupName | String | Name of the group being fetched |
```
GET /group?groupName=<group_name>
```
#### Response
| Response Variables | Type | Description  |
| ------------- | ------------- | ------------ |
| groupId | int | The GUID of the group being fetched. -1 is returned if it does not exist   |
| groupName | String | The name of the group |
| userIds | int[] | An array containing the GUIDs for all users in the group |
| messageIds | int[] | An array contianing the GUIDs for all messages in the group |
| dateCreated | long | The date the user was created in milliseconds |
| owner | String | The username of the user that created the group |

```
{
	groupId : 56,
	groupName : “Android Chatroom”,
	userIds : [1234, 1236, 2314, … ],
	messageIds : [123456, 546789, …. ],
	dateCreated : 1433453749290,
	owner : “Doug”
}
```
## Message
### Send a message
To send a message, send a POST request to /message as a JSON object containing the parameters: String : from (username), String : to (user or chatroom name), and String : content. A successful request will be responded with boolean : success (true), a unique int : messageId, a unique int : groupId and a long : dateCreated. An unsuccessful request will be responded with a boolean : success (false) and an error object with a code and description of the error. 
#### Request
| Request Parameter | Type | Description  |
| ------------- | ------------- | ------------ |
| sender | String | The userName of the user sending the message  |
| recipient | String | The userName/groupName of the user/group receiveing the message |
| content | String | The contents of the message being sent |
```
POST /message
{
 	sender: “Doug”,
	recipient: “John”,		// Chatroom names can also be passed 
	content: "Hey"
}

```
#### Response
| Response Variables | Type | Description  |
| ------------- | ------------- | ------------ |
| success | boolean | Returns true if the message was delivered  |
| messageId | int | The generated GUID for the message |
| groupId | int | The GUID of the group the message belongs to |
| dateCreated | long | The date the message was created in milliseconds |
```
Success
{
	success : true,
	messageId : 345224,
	groupId : 56,		// A message to only 1 user will still have a groupId
	dateCreated : 1433453749290	// In milliseconds
}
```
| Response Variables | Type | Description  |
| ------------- | ------------- | ------------ |
| success | boolean | Returns false if the message was not delivered  |
| error | Object | JSON Object containing an error code and description |
| code | int | An error code that is paired with a description of the error |
| description | String | A description as to why the message was not delivered |
```
Failure
{
	success : false
	error : {
		code : “1”,	// List of error codes will be created
		description : “User ‘George P. Burdell’ does not exist” 
	}
}
```

### Get a message
#### Request
| Request Parameter | Type | Description  |
| ------------- | ------------- | ------------ |
| id | String | The messageId for the message being fetched |
```
GET /message?id=<message_id>
```
#### Response
| Response Variable | Type | Description  |
| ------------- | ------------- | ------------ |
| sender | String | The userName of the sender |
| recipient | String | The userName of the recipient |
| content | String | The contents of the message |
| dateCreated | long | The date the message was created in milliseconds |
| documentId | int | (Optional) The GUID of the document attached to the message |
```
{
	sender : "Doug",
	recipient : "Bill",
	content : "Hey Bill, I send messages",
	dateCreated : 1433453749290,
	documentId : 234
}
```

## Document
### Send a document
#### Request
| Request Parameter | Type | Description  |
| ------------- | ------------- | ------------ |
| messageId | int | The messageId for the message the document is attached to |
| contentType | String | The MIME content type of the document |
| content | TBD | The encoded resource, encoding method to be determined |
```
POST /document
{
  messageId: 123456,
  contentType: "image/png",
  content: "...base 64 encoded..."
}
```
#### Response

### Get a document
#### Request
```
GET /document?id=<document_id>
```
#### Response



