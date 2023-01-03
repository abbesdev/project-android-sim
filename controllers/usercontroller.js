const Classroom = require("../models/classroom");
const TimeTable = require("../models/timetable");
const User = require("../models/user");


exports.adminBoard = (req, res) => {
    res.status(200).send("admin Content.");
  };
  
  exports.teacherBoard = (req, res) => {
    res.status(200).send("teacher Content.");
  };
  
  exports.parentBoard = (req, res) => {
    res.status(200).send("parent Content.");
  };
  
  exports.studentBoard = (req, res) => {
    res.status(200).send("student Content.");
  };

exports.findUser = async (req, res) => {
  try{
  const user = await User.findById(req.params.id);
  res.send(user);}
  catch{
    res.status(400).send({error : "User not found"});
  }
};
exports.findallUsers = async (req, res) => {
  try{
  const user = await User.find();
  res.status(200).json( user);}
  catch{
    res.status(400).send({error : "User not found"});
  }
};
// count users number
exports.countUsers = async (req, res) => {
  try{
    let users = [0,0,0,0]

  const user = await User.countDocuments();
   users[0] = user;
   const teachers = await User.countDocuments({roles : "63862832351815a82140b1bf"});
    users[1] = teachers;
    const students = await User.countDocuments({roles : "63862832351815a82140b1c2"});
    users[2] = students;
    const parents = await User.countDocuments({roles : "63862832351815a82140b1c1"});
    users[3] = parents;

  res.status(200).json( users);}
  catch{
    res.status(400).send({error : "User not found"});
  }
};
// count users number where role is student
exports.countStudents = async (req, res) => {
  try{
  const user = await User.countDocuments({roles : "63862832351815a82140b1c2"});
  res.status(200).json( user);}
  catch{
    res.status(400).send({error : "User not found"});
  }
};
// count users number where role is teachers
exports.countTeachers = async (req, res) => {
  try{
  const user = await User.countDocuments({roles : "63862832351815a82140b1bf"});
  res.status(200).json( user);}
  catch{
    res.status(400).send({error : "User not found"});
  }
};
// count users number where role is parents
exports.countParents = async (req, res) => {
  try{
  const user = await User.countDocuments({roles : "63862832351815a82140b1c1"});
  res.status(200).json( user);}
  catch{
    res.status(400).send({error : "User not found"});
  }
};
exports.updateUser = async (req, res) => {
  try{
    const user = await User.findById(req.params.id);
    var id = req.params.id
    Object.assign(user, req.body);
    await user.update(user, {$set: req.body});
    res.send(user);
  } catch(e) {
    res.status(404).send({error : "User not found"});
    res.status(400).send({error : e});
  }
};
exports.deleteUser = async (req, res) => {
   try{
    const user = await User.findById(req.params.id);
    Object.assign(user, req.body);
    user.delete();
    res.send({data : "user deleted successfully"});
  } catch {
    res.status(404).send({error : "User not found"});
  }
};  

// update confirmed to true
exports.confirmation = async (req, res) => {
  try{
   // console.log(req.params.id);
    const user = await User.findById(req.params.id);
    user.confirmed = true;
    user.save();
    res.send(user);
  } catch(e) {
    res.status(404).send({error : "User not found"});
    res.status(400).send({error : e});
  }
};

  //get all timetables by parent id
  exports.getTimetables = async (req, res) => {
    try{
    const user = await User.findById(req.params.id);
    console.log(user.childrens[0]);
    const user2 = await User.find(user.childrens[0]);
    console.log(user2);
    const timetables = await TimeTable.find({classes : user2[0].classes});


    res.status(200).send(timetables);
    } catch(e) {
      res.status(404).send({error : "User not found"});
      res.status(400).send({error : e});
    }
  };
   //get all timetables by parent 
   exports.getTimetablestudent = async (req, res) => {
    try{
    const student = await User.findById(req.params.id);
    //console.log(user.childrens[0]);
  
    const timetables = await TimeTable.find({classes : student.classes});


    res.status(200).send(timetables);
    } catch(e) {
      res.status(404).send({error : "User not found"});
      res.status(400).send({error : e});
    }
  };

  
  exports.getTimetableteacher = async (req, res) => {
    try{

    const classrooms = await Classroom.find({teacher : req.params.id});
    //console.log(user.childrens[0]);
    const timetables = await TimeTable.find({classes : student.classes});
  
  //  const timetables = await TimeTable.find({classes : student.classes});


    res.status(200).send(classrooms);
    } catch(e) {
      res.status(404).send({error : "User not found"});
      res.status(400).send({error : e});
    }
  };
//exports.
