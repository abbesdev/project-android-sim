const { authJwt } = require("../middlewares");
const controller = require("../controllers/usercontroller");
const authcontroller = require("../controllers/authcontroller");
const { models } = require("mongoose");

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
  //update user by id
  app.patch(
    "/api/test/updateuser/:id",
    controller.updateUser
  );
app.get(
  "/confirmation/:token",
  authcontroller.confirm
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