const mongoose = require("mongoose");
var UploadFile = mongoose.model(
    "uploads.files",
    new  mongoose.Schema(
      {
        "_id": {
          "type": "ObjectId"
        },
        "length": {
          "type": "Number"
        },
        "chunkSize": {
          "type": "Number"
        },
        "uploadDate": {
          "type": "Date"
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