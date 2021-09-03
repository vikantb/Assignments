'use strict';

const sequelize=require('../database');
const Blog=require('../models/blog');

sequelize.sync();

const getBlogs = async (req, res, next) => {
    Blog.findAll({ raw: true })
        .then((blogs) =>res.status(200).send(blogs))
        .catch(err=> res.status(503).send(`Error: ${err.message}`) );
}

const getBlog = async (req, res, next) => {
    const id=req.params.id ;
    const blog= await findBlogById(id);
    if(!blog){
        res.status(400).send("Error : no blog with "+id);
        return;
    }
    res.status(200).json(blog);
}

const add = (req, res, next) => {
    Blog.create(req.body)
        .then((result) => res.status(201).send(req.body))
        .catch((err) => res.status(406).send(err.message));
}

const findBlogById =  async (id) => {
    const blog = await Blog.findOne({where: {id: id},raw: true}) ;
    return blog;
}

const deleteBlog = async (req, res, next) => {
    const id=req.params.id ;
    const blog= await findBlogById(id);

    if(!blog){
        res.status(400).send("Error : no blog with "+id);
        return;
    }
    Blog.destroy({where: {id: id}})
            .then(() => res.send(blog));
}

const editBlog =  async (req, res, next) => {
    const id=req.params.id ;
    const blog= await findBlogById(id);
    if(!blog){
        res.status(400).send("Error : no blog with "+id);
        return;
    }
    Blog.update(req.body,{ where:{ id: id }})
              .then(() => res.send(blog))
              .catch((err) => res.status(406).send(err.message));;
}

module.exports = {
    getBlogs,
    getBlog,
    add,
    deleteBlog,
    editBlog
}