const database = process.env.MONGO_INITDB_DATABASE; // Get the database name
const conn = new Mongo().getDB(database);

// Insert a document to automatically create a collection
conn.my_collection.insertOne({key: "value"}); // Replace 'my_collection' with your collection name

print(`Database '${database}' and collection 'my_collection' initialized with a document.`);