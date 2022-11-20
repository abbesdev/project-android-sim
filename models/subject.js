const mongoose = require("mongoose");

var Subject = mongoose.model(
  "Subject",
  new mongoose.Schema({
    
    nameSubject:String,
    
   
  
   
  })
);

module.exports = Subject;