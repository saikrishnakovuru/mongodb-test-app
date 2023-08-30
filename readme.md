Notes on MongoDB 

# what is mongo db


Swagger dependency

``` java
<dependency>
	<groupId>org.springdoc</groupId>
	<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
	<version>2.0.0</version>
</dependency>
```

## The below line has to be added on the pojo class 
pojo is often referred as document because, a document in MongoDB is essentially a JSON-like structure that represents a piece of data.

In MongoDB, a collection is a grouping of MongoDB documents. It is the equivalent of a table in a relational database. Each collection
exists within a specific database and can hold a set of documents that represent related data.

``` java
@Document(collection = "JobPost");
```

To find the URI added in application.properties go to mongo atlas
login with mail id -> database-> under database click connect -> select drivers and URL will ge given
modify the URI having <password> with correct password(saikrishna).

> The hierarchy is as follows: within a MongoDB cluster, you can have multiple databases, each database can contain multiple collections, and each collection holds individual documents.

## Collection vs Document
> Collection is a group of mongoDB documents, It's analogous to a table in relational databases.

>A document is a basic unit of data in MongoDB. It's similar to a row in a relational database, but MongoDB documents are stored in a flexible, JSON-like format known as BSON (Binary JSON).
--> we can assume Collecation as a table and Document as a row in that table.
        