const mongoose = require("mongoose");

var User = mongoose.model(
  "User",
  new mongoose.Schema({
    fullname:String,
    email: String,
    password: String,
    roles: [
      {
        type: mongoose.Schema.Types.ObjectId,
        ref: "Role"
      }
    ],
    resetLink: {
      data: String,
      default: ''
    }
  })
);

module.exports = User;