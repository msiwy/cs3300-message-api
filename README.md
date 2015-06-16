p1server
========

Web Server / REST API

# Specifications
## Authentication 
### Authenticate a user
To authenticate a user, send a GET request to /auth with the parameter name set to your username. This request is responded by a unique int : userId. -1 is returned if the username does not exist
#### Request
| Request Parameter       | Type          | Description  |
| -------------   | ------------- | ------------ |
| username            | String	| Username being authenticated. |

```
GET /users/auth?username=<user_name>

GET /users/auth?username=tim

GET /users/auth?username="Tim"
```

#### Response
| Response Variable        | Type          | Description  |
| -------------   | ------------- | ------------ |
| userId          | int	          | The GUID generated. Returns -1 on fail  |
| username | String | A user's name. Returns null on fail|
```
{
	"userId" : 1234		// -1 if user does not exist
	"username": Cruise // NULL if user does not exist
}
```
## Users
### Get all users
To request a list of all existing users, send a GET request to /users with no parameters. This will return a JSON list of user's usernames as Strings
#### Request
```
GET /users/all
```
#### Response
| Response Variable       | Type          | Description  |
| -------------   | ------------- | ------------ |
| username | String | A user's name |
| userId | int | A user's GUID |
```
[
	{ "username" : "Doug", "userId" : 123674 },
	{ "username" : "Bill", "userId" : 61563 },
	{ "username" : "Tom", "userId" : 25333 },
	{...}
]
```

### Get user info
   To request information regarding an existing user, send a GET request to /users with the parameter userId.
   #### Request
   
   ```
   GET /users/<user_id>
   GET /users/66965
   ```
   
   #### Response
   | Response Parameter       | Type          | Description  |
   | -------------   | ------------- | ------------ |
   | username | String | The username of the user in interest |
   | userId            | int	  | The GUID for the user |
   ```
   {
   	"username":"Bob",
   	"userId":66965
   }
   ```  
   
### Get all groups that a user is in
To request information regarding which groups a user is in, send a GET request to /users/<userId>/groups with the parameter userId.
This will return a JSON list of groupnames and groupIds.
#### Request
```
GET /users/<userId>/groups
GET /users/66965/groups
```

#### Response
| Response Parameter       | Type          | Description  |
| -------------   | ------------- | ------------ |
| groupId | int	  | The GUID for the user |
| groupname | String | The username of the user in interest |

```
[
   { "groupId":24328, "groupname":"Water Cooler" },
   { "groupId":93243, "groupname":"CS 3251 Project" },
   { ... }
]
```

### Create a user
To create a user, send a POST request to /users passing the parameter username. The username and userId will be returned. 
#### Request
| Request Parameter | Type | Description  |
| ------------- | ------------- | ------------ |
| username | String | Name of the user being created |
```
POST /users?username=<username>
POST /users?username=Ted
POST /users?username="Ted"
```

#### Response
| Response Variable | Type | Description  |
| ------------- | ------------- | ------------ |
| userId | int | The GUID of the user |
| username | String | The name of the user |
```
	{ "username" : "Ted", "userId" : 2345546 }
```
   
### Update user info
To update information of an existing user, send a POST request to /users/<userId>/update with the parameter username.
#### Requests
```
POST /users/<user_id>/update?username=<username>
POST /users/66965/update?username=Tom
POST /users/66965/update?username="Tom"
```
#### Response
| Response Parameter       | Type          | Description  |
| -------------   | ------------- | ------------ |
| userId            | int	  | The GUID for the user |
| username | String | The username of the user in interest |
```
{
	"userId":66965,
	"username":"Bob"
}
``` 
### Delete a user
To delete a user, send a DELETE request to /users passing the parameter userId. The username and userId will be returned. 
#### Request
| Request Parameter | Type | Description  |
| ------------- | ------------- | ------------ |
| userId | int | Name of the user being deleted |
```
DELETE /users?userId=<userId>
DELETE /users?userId=123
```

#### Response
| Response Variable | Type | Description  |
| ------------- | ------------- | ------------ |
| status | boolean | Success/Fail Status of request |
```
Success
	{ "status" : true }
Fail
    { "status" : false }
```
   
## Group
### Get all groups
To request a list of all existing groups, send a GET request to /groups/all. Note that whenever a message is sent a group is created, so not all groups will have groupnames
#### Request
```
GET /groups/all
```
#### Response
| Response Parameter       | Type          | Description  |
| -------------   | ------------- | ------------ |
| groupId | int | The group's GUID |
| groupname | String | Name of the group |

```
[
	{ "groupname" : "Klaus", "groupId" : 1 },
	{ "groupname" : "SW", "groupId" : 2 },
	{ "groupname" : "Default", "groupId" : 3 } // Default will be String of group member's userIds
]
```
### Get a group's info by groupId
To request a list of all existing groups, send a GET request to /groups/all. Note that whenever a message is sent a group is created, so not all groups will have groupnames
#### Request
```
GET /groups/<groupId>
```
#### Response
| Response Parameter       | Type          | Description  |
| -------------   | ------------- | ------------ |
| groupname | String | Name of the group |
| groupId | int | The group's GUID |

```
{ "groupname":"Klaus", "groupId":1 }
```
### Get all users in a group
#### Request
```
GET /groups/{groupId}/users
```
#### Response
```
[
	{"userId" : 1234, "username" : "Ted"},
	{"userId" : 1235, "username" : "Bill"},
	{...}
]
```
### Get all messages in a group
#### Request
```
GET /groups/{groupId}/messages
```
#### Response
```
[
	{ "messageId": 100, "senderId" : 1000, "dateCreated" : 34623754762354, "content" : "Hey Doug." },
	{ "messageId": 101, "senderId" : 3295, "dateCreated" : 34623754762378, "content" : "Hey Ted, what is up." },
	{ ... }
]
```

### Create a group
To create a group, send a POST request to /groups with the parameter groupname
#### Request
| Request Parameter | Type | Description  |
| ------------- | ------------- | ------------ |
| groupName | String | The desired name of the group |
```
POST /groups/create?groupname="Chatroom"
```
#### Response
| Response Variable | Type | Description  |
| ------------- | ------------- | ------------ |
| groupName | String | The desired name of the group |
| groupId | int | The GUID for the group |
```
{ "groupname":"Chatroom", "groupId":1502940243 }
```

### Add a User to a group
To add a user to a group, send a POST request to /groups with the parameter groupname
#### Request
| Request Parameter | Type | Description  |
| ------------- | ------------- | ------------ |
| groupId | int | The GUID for the group |
| userId | int | The GUID for the user |
```
POST /groups/add?groupId=<groupId>&userId=<userId>
```
#### Response
| Response Variable | Type | Description  |
| ------------- | ------------- | ------------ |
| groupId | String | The desired name of the group |
| participantIds | int[] | The GUID for the users |
```
Success
{
	"groupId" : 100,
	"participantId" : [1,2,3,4]
}
Failure
{
	-1
}
```

## Messages
### Send a message
To send a message, send a POST request to /message as a JSON object containing the parameters: senderId, recipientIds, and content.
#### Request
| Request Parameter | Type | Description  |
| ------------- | ------------- | ------------ |
| senderId | int | The userId of the user sending the message  |
| recipientIds | int[] | The userIds of the users receiving the message |
| content | String | The contents of the message being sent |
```
POST /messages
{	
	"senderId" : 1234, // this is the current user's userId
	"recipientIds" : [2345, 1233, 1222]
	"content" : "Hey guys"
}
```
#### Response
| Response Variables | Type | Description  |
| ------------- | ------------- | ------------ |
| success | boolean | Returns true if the message was delivered  |
| messageId | int | The generated GUID for the message |
| groupId | int | The GUID of the group the message belongs to |
```
Success
{
	"success" : true,
	"messageId" : 100,
	"groupId": : 44 // We determine if a new group is created or if it already exists
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
	"success" : false
	"error" : {
		"code" : “1”,	// List of error codes will be created
		"description" : “User ‘George P. Burdell’ does not exist” 
	}
}
```
## Notifications
### Get a user's notifications
The client should periodically send GET requests to  /notifications/{userId} to retrieve notifications on messages sent to that user.
#### Request
```
GET /notifications/{userId}
```
#### Response
```
[
	{ "messageId": 100, "senderId" : 1234, "dateCreated" : 34623754762354, "content" : "Hey Doug", },
	{ "messageId": 101, "senderId" : 1235, "dateCreated" : 34623754762378, "content" : "Hey Ted, thanks for the text",  }
]
```

## Document TO BE DETERMINED
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


