'use strict';
var mongoose = require('mongoose');
var Schema = mongoose.Schema;


var UserSchema = new Schema({
  firstname: {
    type: String,
    required: 'Enter the name of the user. '
  },
  lastname: {
    type: String,
    required: 'Enter the last name of the user. '
  },
  birthday: {
    type: Date,
    default: Date.now
  },
  email: {
    type: String,
    default: ""
  }
});

var TaskSchema = new Schema({
  name: {
    type: String,
    required: 'Enter the name of the task'
  },
  Created_date: {
    type: Date,
    default: Date.now
  },
  status: {
    type: [{
      type: String,
      enum: ['pending', 'ongoing', 'completed']
    }],
    default: ['pending']
  }
});


module.exports = mongoose.model('Users', UserSchema);
module.exports = mongoose.model('Tasks', TaskSchema);