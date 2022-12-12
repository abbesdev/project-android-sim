const mongoose = require("mongoose");
const grade = require("./grade");

var User = mongoose.model(
  "User",
  new mongoose.Schema({
    fullname:String,
    email: String,
    password: String,

    confirmed: {
      type: Boolean,
      default: false
    },
    roles: [
      {
        type: mongoose.Schema.Types.ObjectId,
        ref: "Role"
      }
    ],
    childrens: [
      {
        type: mongoose.Schema.Types.ObjectId,
        ref: "Children"
      }
    ],
    classes: [
      {
        type: mongoose.Schema.Types.ObjectId,
        ref: "Classes"
      }
    ],
   
    resetLink: {
      data: String,
      default: ''
    }
  })
);

module.exports = User;