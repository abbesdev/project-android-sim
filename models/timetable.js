const mongoose = require("mongoose");
//import Classes from '../models/classes.js';
const Classes = require("../models/classes.js");



var TimeTable = mongoose.model(
  "TimeTable",
  new mongoose.Schema({
    
    title : String,
    classes: [
        {
          type: mongoose.Schema.Types.ObjectId,
          ref: "Classes"
        }
      ],
      className: String,
   
  /*     Classes.findOne({ "nameClasses": req.params.nameClasses })*/
    startdate: Date,
    enddate: Date, 

   
  })
);

module.exports = TimeTable;