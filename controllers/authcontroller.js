const config = require("../config/authconfig");
const db = require("../models");
const User = db.user;
const Role = db.role;
const nodemailer = require("nodemailer");
const users = require("../models/user.js");
var jwt = require("jsonwebtoken");
var bcrypt = require("bcryptjs");



const transporter = nodemailer.createTransport({
  host: "smtp.mailtrap.io",
  port: 2525 ,
  auth: {
    user: "5aebbd9cfde306",
    pass: "bd86be6e80eabd"
  },
});
const EMAIL_SECRET = 'asdf1093KMnzxcvnkljvasdu09123nlasdasdf';
exports.signup = (req, res) => {
  const user = new User({
    fullname: req.body.fullname,
    email: req.body.email,
    password: bcrypt.hashSync(req.body.password, 8)
  });

  user.save((err, user) => {
    if (err) {
      res.status(500).send({ message: err });
      return;
    }

    if (req.body.roles) {
      Role.find(
        {
          name: { $in: req.body.roles }
        },
        (err, roles) => {
          if (err) {
            res.status(500).send({ message: err });
            return;
          }

          user.roles = roles.map(role => role._id);
          user.save(err => {
            if (err) {
              res.status(500).send({ message: err });
              return;
            }
/************************************* */
            jwt.sign(
              {
                id: user.id,
              },
              EMAIL_SECRET,
              {
                expiresIn: '1d',
              },
              (err, emailToken) => {
                const url = `http://localhost:8080/confirmation/${emailToken}`;
      
                transporter.sendMail({
                  to: user.email,
                  subject: 'Confirm Email',
                  html: `Please click this email to confirm your email: <a href="${url}">${url}</a>`,
                });
              },
            );
/**************** */
            res.send({ message: "User was registered successfully!" });
          });
        }
      );
    } else {
      Role.findOne({ name: "admin" }, (err, role) => {
        if (err) {
          res.status(500).send({ message: err });
          return;
        }

        user.roles = [role._id];
        user.save(err => {
          if (err) {
            res.status(500).send({ message: err });
            return;
          }

          res.send({ message: "User was registered successfully!" });
        });
      });
    }
  });
};
exports.confirm = async (req, res) => {
  try{
    const id = req.param.id;
    const user = await User.findById(id.id);
    const theid = id.id;
    await users.findByIdAndUpdate({_id:id},{confirmed: true});
    res.send("confirmed");
  }catch(e){
    res.send(e);
  }
   
};
exports.signin = (req, res) => {
  User.findOne({
    email: req.body.email
  })
    .populate("roles", "-__v")
    .exec((err, user) => {
      if (err) {
        res.status(500).send({ message: err });
        return;
      }

      if (!user) {
        return res.status(404).send({ message: "User Not found." });
      }

      if(!user.confirmed){
        var tokenn = jwt.sign({ id: user.id }, config.secret, {
          expiresIn: 86400 // 24 hours
        });
        return res.status(404).send({ message: "User not confirmed.",        accessToken: tokenn, id: user._id
      });
      }

      var passwordIsValid = bcrypt.compareSync(
        req.body.password,
        user.password
      );

      if (!passwordIsValid) {
        return res.status(401).send({
          accessToken: null,
          message: "Invalid Password!"
        });
      }

      var token = jwt.sign({ id: user.id }, config.secret, {
        expiresIn: 86400 // 24 hours
      });

      var authorities = [];

      for (let i = 0; i < user.roles.length; i++) {
        authorities.push("ROLE_" + user.roles[i].name.toUpperCase());
      }
      var authoritiesC = [];

      for (let i = 0; i < user.childrens.length; i++) {
        authoritiesC.push(user.childrens[i]._id);
      }
     
      res.status(200).send({
        id: user._id,
        fullname: user.fullname,
        email: user.email,
        roles: authorities,
        accessToken: token,
        childrens: authoritiesC
      });
    });
};