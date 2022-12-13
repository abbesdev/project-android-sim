const mongoose = require("mongoose");
var UploadFile = mongoose.model(
    "UploadFile",
    new  mongoose.Schema({
        "_id": {
          "$oid": {
            "type": "ObjectId"
          }
        },
        "length": {
          "$numberInt": {
            "type": "Date"
          }
        },
        "chunkSize": {
          "$numberInt": {
            "type": "Date"
          }
        },
        "uploadDate": {
          "$date": {
            "$numberLong": {
              "type": "String"
            }
          }
        },
        "filename": {
          "type": "String"
        },
        "contentType": {
          "type": "String"
        }
      }
));

module.exports = UploadFile;