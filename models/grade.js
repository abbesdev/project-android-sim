const mongoose = require("mongoose");

const Grade = mongoose.Schema({
 
    subject: [
        {
          type: mongoose.Schema.Types.String,
          ref: "Subject"
        }
      ],
    gradeName: {type :String},
    
   gradeValue: {type:String}
  
}
  
);

module.exports = Grade;