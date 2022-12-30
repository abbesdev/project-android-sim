const express = require('express');
const { verifyToken } = require('../middlewares/authJwt.js');
const Model = require('../models/timetable.js');
const router = express.Router();
const Modelclasses = require('../models/classes.js');

const Classes = require('../models/classes.js');


//const controller = require("../controllers/timetablecontroller");


//Post Method
router.post('/post', async (req, res) => {
    const data = new Model({
        title: req.body.title,
            classes: req.body.classes,
            startdate: req.body.startdate,
            enddate: req.body.enddate,
      
    })

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
       
      
        res.json(data)   ;
      


    }
    catch (error) {
       res.status(500).json({ message: error.message  + "errcor"})
       // res.status(500).json({ data })

    }
})


//Delete by ID Method
router.delete('/delete/:id', async (req, res) => {
    try {
        const id = req.params.id;
        const data = await Model.findByIdAndDelete(id)
        res.send(`Document with ${data.title} has been deleted..`)
    }
    catch (error) {
        res.status(400).json({ message: error.message })
    }
})

// get by classes[0] and startdate
router.get('/getOne/:classes/:startdate', async (req, res) => {
    try {
        const data2 =  await Modelclasses.find({nameClasse: req.params.classes});
       //const data3  = await Model.find({classes: data2[0]._id} && {startdate: req.params.startdate});
    //   const date = req.params.startdate;


    const startdate = new Date(req.params.startdate);
startdate.setHours(0, 0, 0, 0);
const enddate = new Date(startdate);
enddate.setDate(enddate.getDate() + 1);
       const date = new Date(req.params.startdate);
console.log("ddd "+data2[0]._id);
       const data3  = await Model.find( {classes: data2[0]._id , startdate: { $gte: startdate, $lt: enddate }});

     //   const data = await Model.find({classes: req.params.classes, startdate: req.params.startdate});
        res.json(data3)
    }
    catch (error) {
        res.status(500).json({ message: error.message })
    }
})










/*
//Get by ID Method
router.get('/getOne/:id',[verifyToken], async (req, res) => {
    try {
        const data = await Model.findById(req.params.id);
        res.json(data)
    }
    catch (error) {
        res.status(500).json({ message: error.message })
    }
})

//Update by ID Method
router.patch('/update/:id',[verifyToken], async (req, res) => {
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
router.delete('/delete/:id',[verifyToken], async (req, res) => {
    try {
        const id = req.params.id;
        const data = await Model.findByIdAndDelete(id)
        res.send(`Document with ${data.nameSubject} has been deleted..`)
    }
    catch (error) {
        res.status(400).json({ message: error.message })
    }
})
*/
module.exports = router;
