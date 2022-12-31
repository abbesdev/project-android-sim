const mongoose = require("mongoose");

var Classroom = mongoose.model(
  "Classroom",
  new mongoose.Schema({
    classroomTitle:String,

    
    classes: [
        {
          type: mongoose.Schema.Types.ObjectId,
          ref: "Classes"
        }
      ],
      students: [
        {
          type: mongoose.Schema.Types.ObjectId,
          ref: "User"
        }
      ],
      teacher: [
        {
          type: mongoose.Schema.Types.ObjectId,
          ref: "User"
        }
      ],
      subject: [
        {
          type: mongoose.Schema.Types.ObjectId,
          ref: "Subject"
        }
      ],
      homework: [
        {
          type: mongoose.Schema.Types.ObjectId,
          ref: "Homework"
        }
      ],
    
    
   
  
   
  })
);

module.exports = Classroom;