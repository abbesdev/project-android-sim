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
  res.send({data : user});}
  catch{
    res.status(400).send({error : "User not found"});
  }
};
exports.updateUser = async (req, res) => {
  try{
    const user = await User.findById(req.params.id);
    Object.assign(user, req.body);
    user.save();
    res.send({data : user});
  } catch {
    res.status(404).send({error : "User not found"});
  }
};

//exports.
