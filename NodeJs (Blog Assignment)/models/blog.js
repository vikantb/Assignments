const Sequelize=require('sequelize');
const sequelize=require('../database');

const Blog = sequelize.define('blogs',{
    id:{
        type: Sequelize.INTEGER,
        autoIncrement:true,
        allowNull:false,
        primaryKey:true
    },
    title:{
        type: Sequelize.STRING,
        allowNull:false,
        validate:{
            notEmpty: true,
        }
    },
    categories:{
        type: Sequelize.STRING,
        allowNull:false,
        validate:{
            notEmpty: true,
        }
    },
    content: {
        type: Sequelize.STRING,
        allowNull:false,
        validate:{
            notEmpty: true,
        }
    }
});

module.exports = Blog ;