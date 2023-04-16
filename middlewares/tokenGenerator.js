const jwt = require('jsonwebtoken');
const { v4: uuidv4 } = require('uuid');

function createResetToken(userId) {
  const token = jwt.sign({ userId, uuid: uuidv4() }, process.env.JWT_SECRET, {
    expiresIn: '1h'
  });
  return token;
}