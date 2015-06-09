p1server
========

Web Server / REST API

# Specifications
## Authentication 
### Authenticate a user
To authenticate a user, send a GET request to /auth with the parameter name set to your username. This request is responded by a unique int : userId. -1 is returned if the authentication fails, for example if parameter name is an empty String
#### Request
| Request Parameter       | Type          | Description  |
| -------------   | ------------- | ------------ |
| name            | String	| User name being authenticated. |
```
GET /auth?name=<user_name>

GET /auth?name=tim
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
### Get all users
To request a list of all existing users, send a GET request to /users with no parameters. This will return a JSON list of user's usernames as Strings
#### Request
```
GET /users
```
#### Response
| Response Variable       | Type          | Description  |
| -------------   | ------------- | ------------ |
| users | JSON Object | A list of usernames |
```
{
	users : [
		{ username : "Doug", userId : 1234, dateCreated :  1433453749290,  groups : [1, 12, 56]},
		{ username : "Bill", userId : 1563, dateCreated :  1433453768090,  groups : [2, 22] },
		{ username : "Tom", userId : 2333, dateCreated :  143345377790,  groups : [4, 30, 80, 99] },
		{...}
	]
}
```

### Get user info
To request information regarding an existing user, send a GET request to /users with the parameter name set to your username. This request is responded by a unique int : userId, long : dateCreated, int[] : groups. 
#### Request
| Request Parameter       | Type          | Description  |
| -------------   | ------------- | ------------ |
| userName            | String	  |  The User Name for the user you are fetching|
```
GET /users?userName=<user_name>

GET /users?userName=david
```
#### Response
| Response Parameter       | Type          | Description  |
| -------------   | ------------- | ------------ |
| userId            | int	  | The GUID for the user |
| dateCreated | long | The date the user was created in milliseconds |
| groups | int[] | An array of groupId's the user belongs to |
```
{
	userId : 1234,
	dateCreated :  1433453749290,	// In milliseconds
	groups : [1, 12, 56],		// Group IDs	
}
```
### Create a user
To create a user, send a POST request to /users passing the parameter newUser. An int : userId and long : dateCreated will be returned. 
#### Request
| Request Parameter | Type | Description  |
| ------------- | ------------- | ------------ |
| newUser | String | Name of the user being created |
```
POST /users?newUser=<new_user_name>

POST /users?newUser=ted
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
### Get all groups
To request a list of all existing groups, send a GET request to /groups with no parameters.
#### Request
```
GET /groups
```
#### Response
| Response Parameter       | Type          | Description  |
| -------------   | ------------- | ------------ |
| groups | JSON Object | JSON Object that contains an array of group information |
```
{
	groups : [
		{ groudId : 56, groupName : "Android Chatroom", owner : "Doug", userIds : [1234, 1345], messageIds : [123456, 234567, ...], dateCreated : 1433453749290 },
		{ groudId : 45, groupName : "iOS Chatroom", owner : "Ted", userIds : [1254, 1347], messageIds : [123486, 234569, ...], dateCreated : 1433453749890 },
		{...}
	]
	
}
```

### Create a group
To create a group, send a POST request to /groups with a JSON object containing the String : owner and String : groupName.
#### Request
| Request Parameter | Type | Description  |
| ------------- | ------------- | ------------ |
| owner | String | The userName of the group creator |
| groupName | String | The desired name of the group |
```
POST /groups
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
| owner | String | The owner of the group |
| userIds | int[] | An array containing the GUIDs for all users in the group, this will initially only have the owner's userId |
```
{
	success : true,
	groupId : 56,
	owner : "Doug",
	userIds : 1234		// The owner's userId
}
```
### Get a group's info
#### Request
| Request Parameter | Type | Description  |
| ------------- | ------------- | ------------ |
| groupName | String | Name of the group being fetched |
```
GET /groups?groupName=<group_name>

GET /groups?groupName=Hangout
```
#### Response
| Response Variables | Type | Description  |
| ------------- | ------------- | ------------ |
| groupId | int | The GUID of the group being fetched. -1 is returned if it does not exist   |
| groupName | String | The name of the group |
| userIds | int[] | An array containing the GUIDs for all users in the group |
| messageIds | int[] | An array containing the GUIDs for all messages in the group |
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
To send a message, send a POST request to /message as a JSON object containing the parameters: String : from (username), String : to (user or chatroom name), and String : content. A successful request will be responded with boolean : success (true), an int : messageId, an int : groupId and a long : dateCreated. An unsuccessful request will be responded with a boolean : success (false) and an error object with a code and description of the error. 
#### Request
| Request Parameter | Type | Description  |
| ------------- | ------------- | ------------ |
| sender | String | The userName of the user sending the message  |
| recipient | String | The userName/groupName of the user/group receiving  the message |
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

GET /message?id=123456
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
  messageId : 123456,
  contentType : "image/png",
  content : "...base 64 encoded..."
}
```
#### Response
| Response Variable | Type | Description  |
| ------------- | ------------- | ------------ |
| documentId | int | The GUID generated for the document |
```
{
	documentId : "2345"
}
```
### Get a document
#### Request
```
GET /document?id=<document_id>

GET /document?id=2345
```
#### Response
| Response Variable | Type | Description  |
| ------------- | ------------- | ------------ |
| documentName | String | The name of the document |
| contentType | String | The MIME content type of the document |
| content | TBD | The encoded resource, encoding method to be determined |
| messageId | int | The messageId for the message the document is attached to |
```
{
	documentName : "Grocery_List",
	contentType : "text/html",
	content : "...base 64 encoded..."
	messageId : 123456
}
```
## Bots
### To be determined...


