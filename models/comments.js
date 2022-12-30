const mongoose = require("mongoose");


var Comment = mongoose.model(
    "Comment",
    new mongoose.Schema({
      description : String,

      type : {
        type : String,
        enum : ["remark","attendance"]
      }
      
    })
  );
  
  module.exports = Comment;