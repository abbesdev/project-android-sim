const mongoose = require('mongoose');
mongoose.Promise = global.Promise;

const db = {};

db.mongoose = mongoose;

db.user = require("./user.js");
db.role = require("./role.js");

db.ROLES = ["teacher", "admin", "parent", "student"];

module.exports = db;