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