
const express = require("express");
const cors = require("cors");
//import nodemailer from 'nodemailer';
const nodemailer = require("nodemailer");


bodyParser = require('body-parser');
const app = express();

var corsOptions = {
  origin: "http://localhost:8081"
};

app.use(cors(corsOptions));


app.use(bodyParser.json());
/*
const transporter = nodemailer.createTransport({
  host: "smtp.mailtrap.io",
  port: 465 ,
  auth: {
    user: "5aebbd9cfde306",
    pass: "bd86be6e80eabd"
  },
});
*/
const routes = require('./routes/subjectroutes.js');

app.use('/subject', routes)

const routesC = require('./routes/classesroutes.js');

app.use('/classes', routesC)

const routesCR = require('./routes/classroomroutes.js');

app.use('/classroom', routesCR)


// simple route
app.get("/", (req, res) => {
  res.json({ message: "Welcome to app" });
});
// routes
require('./routes/authroutes')(app);
require('./routes/userroutes')(app);

// set port, listen for requests
const PORT = process.env.PORT || 8080;
app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}.`);
});

const db = require("./models");
const Role = db.role;

db.mongoose
  .connect(`mongodb+srv://test-admin:mohamed1999@school-space.knoykw5.mongodb.net`, {
    dbName: 'school-space',
    useNewUrlParser: true,
    useUnifiedTopology: true
  })
  .then(() => {
    initial();
    console.log("Successfully connect to MongoDB.");
 
  
  })
  .catch(err => {
    console.error("Connection error", err);
    process.exit();
  });

function initial() {
  Role.estimatedDocumentCount((err, count) => {
    if (!err && count === 0) {
      new Role({
        name: "teacher"
      }).save(err => {
        if (err) {
          console.log("error", err);
        }

        console.log("added 'teacher' to roles collection");
      });

      new Role({
        name: "admin"
      }).save(err => {
        if (err) {
          console.log("error", err);
        }

        console.log("added 'admin' to roles collection");
      });

      new Role({
        name: "parent"
      }).save(err => {
        if (err) {
          console.log("error", err);
        }

        console.log("added 'parent' to roles collection");
      });
      new Role({
        name: "student"
      }).save(err => {
        if (err) {
          console.log("error", err);
        }

        console.log("added 'student' to roles collection");
      });
    }
  });
}