const Sequelize = require('sequelize')

const sequelize=new Sequelize("nagarro","root","root",{
    dialect:"mysql",
    host:"localhost"
});

module.exports =sequelize;