const mongoose = require("mongoose");
var Classroom = mongoose.model(
    "Grades",
    new  mongoose.Schema({
        students: [
            {
              type: mongoose.Schema.Types.ObjectId,
              ref: "User"
            }
          ],
    subject: [
        {
          type: mongoose.Schema.Types.String,
          ref: "Subject"
        }
      ],
    gradeName: {type :String},
    
   gradeValue: {type:String}
  
}
  
));

module.exports = Grade;