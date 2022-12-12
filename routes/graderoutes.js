const express = require('express');
const { verifyToken } = require('../middlewares/authJwt.js');
const Model = require('../models/grade.js');
const router = express.Router();

//Post Method
router.post('/post', async (req, res) => {
    const data = new Model({
        gradeName: req.body.gradeName,
        gradeValue: req.body.gradeValue,
        students: req.body.students,
        subject:req.body.subject




      
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
        res.json(data)
    }
    catch (error) {
        res.status(500).json({ message: error.message })
    }
})
//Get by ID Method
router.post('/getByStudentAndSubject', async (req, res) => {
    try {

        const test = await Model.find({ "studentId": req.body.students, "subject":req.body.subject })
        res.send(test)
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
        res.send(`Document with ${data.gradeName} has been deleted..`)
    }
    catch (error) {
        res.status(400).json({ message: error.message })
    }
})

module.exports = router;
