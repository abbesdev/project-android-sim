const mongoose = require("mongoose");

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

// parent inherits from user
var Parent = User.discriminator('Parent', new mongoose.Schema({
  phone_number: String,
  User: [
    {
      type: mongoose.Schema.Types.ObjectId,
      ref: "User"
    }
  ]
}));

// teacher inherits from user
var Teacher = User.discriminator('Teacher', new mongoose.Schema({
  
  User: [
    {
      type: mongoose.Schema.Types.ObjectId,
      ref: "User"
    }
  ]
}));

module.exports = Teacher;

module.exports = Parent;
module.exports = User;