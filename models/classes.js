const mongoose = require("mongoose");

var Classes = mongoose.model(
  "Classes",
  new mongoose.Schema({
    
    nameClasse:String,
    
   
  
   
  })
);

module.exports = Classes;