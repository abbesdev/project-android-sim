const express = require('express');
const { verifyToken } = require('../middlewares/authJwt.js');
const Model = require('../models/homework.js');
const router = express.Router();
const upload = require('../config/fileupload')
const mongoose = require('mongoose');
const mongoURI = `mongodb+srv://test-admin:mohamed1999@school-space.knoykw5.mongodb.net/school-space`;
const Grid = require('gridfs-stream');
const {GridFsStorage, GridFsStream, GridFSBucket} = require('multer-gridfs-storage');


//Connecting to mongo 
const conn = mongoose.createConnection(mongoURI);

//Init gfs 
let gfs; 
conn.once('open', ()=>{
    gridfsBucket = new mongoose.mongo.GridFSBucket(conn.db, {
        bucketName: 'uploads'
      })
    gfs = Grid(conn.db, mongoose.mongo);
    gfs.collection('uploads');
})




router.post('/api/upload', upload.single('image'), function (req, res, next) {
    console.log(req.file);
  })

  router.get('/api/upload/:filename', (req, res)=>{
    gfs.files.findOne({filename:req.params.filename},(err,file)=>{
     
            const readStream = gridfsBucket.openDownloadStreamByName(file.filename);
            readStream.pipe(res)
    })
})


//Post Method
router.post('/post', async (req, res) => {
    const data = new Model(
        {
            homeworkTitle: req.body.homeworkTitle,
            homeworkDeadline : req.body.homeworkDeadline,
            homeworkDescription : req.body.homeworkDescription,
            homeworkFiles : req.body.homeworkFiles,
            homeworkStatus : req.body.homeworkStatus,
            subject : req.body.subject,
            classroom : req.body.classroom
    

        }
    );

    try {
        const dataToSave = await data.save();
        res.status(200).json(dataToSave)
    }
    catch (error) {
        res.status(400).json({ message: error.message })
    }
})

//Get all Method
router.get('/getAll', async (req, res) => {
    try {

        const data = await Model.find();
        res.json(data)
    }
    catch (error) {
        res.status(500).json({ message: error.message })
    }
})

//Get by ID Method
router.get('/getOne/:id', async (req, res) => {
    try {
        const data = await Model.findById(req.params.id);
        res.json(data)
    }
    catch (error) {
        res.status(500).json({ message: error.message })
    }
})

//Update by ID Method
router.patch('/update/:id', async (req, res) => {
    try {
        const id = req.params.id;
        const updatedData = req.body;
        const options = { new: true };

        const result = await Model.findByIdAndUpdate(
            id, updatedData, options
        )

        res.send(result)
    }
    catch (error) {
        res.status(500).json({ message: error.message })
    }
})

//Delete by ID Method
router.delete('/delete/:id', async (req, res) => {
    try {
        const id = req.params.id;
        const data = await Model.findByIdAndDelete(id)
        res.send(`Document with ${data.homeworkTitle} has been deleted..`)
    }
    catch (error) {
        res.status(400).json({ message: error.message })
    }
})

module.exports = router;
