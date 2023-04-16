const express = require('express');
const bodyParser = require('body-parser');
const path = require('path');
const mongoose = require('mongoose');
const multer = require('multer');
const {GridFsStorage} = require('multer-gridfs-storage');
const Grid = require('gridfs-stream');
const methodOverride = require('method-override');

const app = express();

// Middleware
app.use(bodyParser.json());
app.use(methodOverride('_method'));
app.set('view engine', 'ejs');

// Mongo URI
const mongoURI = `mongodb+srv://test-admin:mohamed1999@school-space.knoykw5.mongodb.net/school-space`;

// Create mongo connection
const conn = mongoose.createConnection(mongoURI,{
     dbName: 'school-space',
     useNewUrlParser: true,
     useUnifiedTopology: true});


// Init gfs
let gfs;

conn.once('open', () => {
  // Init stream
  gfs = Grid(conn.db, mongoose.mongo);  
  gfs.collection('uploads');
});

// Create storage engine
const storage = new GridFsStorage({
  url: mongoURI,
  file: (req, file) => {
    return new Promise((resolve, reject) => {
        const filename = file.originalname;
        const fileInfo = {
          filename: filename,
          bucketName: 'uploads'
        };
        resolve(fileInfo);
    });
  }
});

module.exports = upload = multer({ storage})
