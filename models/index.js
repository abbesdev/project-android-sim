const mongoose = require('mongoose');
mongoose.Promise = global.Promise;

const db = {};

db.mongoose = mongoose;

db.user = require("./user");
db.role = require("./role");
db.subject = require("./subject");

db.ROLES = ["teacher", "admin", "parent", "student"];

module.exports = db;