const { authJwt } = require("../middlewares");
const controller = require("../controllers/usercontroller");

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
};