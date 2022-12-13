const mongoose = require("mongoose");
var Homework = mongoose.model(
    "Homework",
    new  mongoose.Schema({
       classroom:
       [
        {
          type: mongoose.Schema.Types.ObjectId,
          ref: "Classroom"
        }
      ],
    subject: [
        {
          type: mongoose.Schema.Types.ObjectId,
          ref: "Subject"
        }
      ],
    homeworkTitle: {type :String},
    
   homeworkDescription: {type:String},

      homeworkFiles: {type :FileList},
      homeworkStatus: {type :Boolean},
      homeworkDeadline: {type :Date},

}
  
));

module.exports = Homework;