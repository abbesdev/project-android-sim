const { authJwt } = require("../middlewares");
const controller = require("../controllers/usercontroller");
const authcontroller = require("../controllers/authcontroller");
const { models } = require("mongoose");
const us = require("../models/user");


module.exports = function(app) {
  app.use(function(req, res, next) {
    res.header(
      "Access-Control-Allow-Headers",
      "x-access-token, Origin, Content-Type, Accept"
    );
    next();
  });

  app.get("/api/test/admin", 
  [authJwt.verifyToken, authJwt.isAdmin], 
  controller.adminBoard);

  app.get("/api/test/teacher", 
  [authJwt.verifyToken, authJwt.isTeacher], 
  controller.teacherBoard);

  app.get(
    "/api/test/parent",
    [authJwt.verifyToken, authJwt.isParent],
    controller.parentBoard
  );

  app.get(
    "/api/test/student",
    [authJwt.verifyToken, authJwt.isStudent],
    controller.studentBoard
  );
  app.get(
    "/api/test/student",
    [authJwt.verifyToken, authJwt.isStudent],
    controller.studentBoard
  );
//get user by id
  app.get(
    "/api/test/getuser/:id",
    controller.findUser
  );
  app.get('/api/test/getUserByClass/:users', async (req, res) => {
    try {

        const data2 =  await us.find({classes: req.params.users});        
        res.json({users : data2[0]._id})
    }
    catch (error) {
        res.status(500).json({ message: error.message })
    }
})
  //get all users
  app.get(
    "/api/test/getallusers",
    controller.findallUsers
  );
  // get users number
  app.get(
    "/api/test/countusers",
    controller.countUsers
  );
   // get students number
   app.get(
    "/api/test/countstudents",
    controller.countStudents
  );
   // get parents number
   app.get(
    "/api/test/countparents",
    controller.countParents
  );

  // get teachers number
  app.get(
    "/api/test/countteachers",
    controller.countTeachers
  );

  app.get(
    "/api/test/getall",
    controller.countTeachers
  );

  // update confirmed to true
  app.patch(
    "/api/test/confirmation/:id",
    controller.confirmation
  );
    

  //update user by id
  app.patch(
    "/api/test/updateuser/:id",
    controller.updateUser
  );
app.patch(
  "/confirmation",async (req, res) => {
  const _id = req.body._id;
 
  let confirmed = req.body.confirmed

  try {
    // This part was changed *****

    await us.findByIdAndUpdate(_id,{confirmed: confirmed})
    res.json('confirmed changed')

    // *******
  } catch (error) {
    console.log(error.message)
  }}
);



   //delete user by id
  app.delete(
    "/api/test/deleteuser/:id",
    controller.deleteUser
  );

  /*
  app.get(
    "/confirmation/:token",async (req, res) => {
      try{
        const {user: {id}} = jwt.verify(req.params.token, EMAIL_SECRET);
        await models.User.updateOne({confirmed: true}, {where: {id}});
      }catch(e){
        res.send('error');
      }
        return res.send('ok');
    }
  )*/
  
};
