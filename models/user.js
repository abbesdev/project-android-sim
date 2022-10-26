const mongoose = require("mongoose");

const User = mongoose.model(
  "User",
  new mongoose.Schema({
    fullname: String,
    email: String,
    password: String,
 //   ecole: String,
    roles: [
      {
        type: mongoose.Schema.Types.ObjectId,
        ref: "Role"
      }
    ]
  })
);

module.exports = User;