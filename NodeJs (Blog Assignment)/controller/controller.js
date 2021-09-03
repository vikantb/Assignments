'use strict';

const sequelize=require('../database');
const Blog=require('../models/blog');

sequelize.sync();

const getAllBlogs =  async (req, res, next) => {
    const blogs= await Blog.findAll({ raw: true }).then((blogs) => blogs );
    res.render('home',{blogs:blogs});
}

const addBlog = (req, res, next) => {
    res.render('addBlog',{error:null});
}

const createNewBlog =  async (req, res, next) => {
    await Blog.create(req.body)
        .then((result) => res.redirect('/') )
        .catch((err) => {
            res.render('addBlog',{error: err.message});
        });
}

const blogShow =  async (req, res, next) => {
    const id=req.params.id ;
    const blog = await Blog.findOne({where: {id: id},raw: true})
                            .then((blog) => blog );
    res.render('showBlog',blog);
}

const deleteBlog =  async (req, res, next) => {
    const id=req.params.id ;
    await Blog.destroy({where: {id: id}})
            .then(() => res.redirect('/'))
            .catch((error) => res.redirect('/') );
}

const editBlogShow =  async (req, res, next) => {
    const id=req.params.id ;
    const blog = await Blog.findOne({where: {id: id},raw: true})
                            .then((blog) => blog );
    res.render('editBlog',{blog:blog,error:null});
}

const editBlog =  async (req, res, next) => {
    const id=req.params.id ;
    await Blog.update(req.body,{ where:{ id: id }})
              .then(() => res.redirect('/'))
              .catch( async (err) => {
                    const blog = await Blog.findOne({where: {id: id},raw: true})
                                            .then((blog) => blog );
                    res.render('editBlog',{blog:blog,error:err.message}) 
              });
}

module.exports = {
    addBlog,
    getAllBlogs,
    createNewBlog,
    blogShow,
    deleteBlog,
    editBlog,
    editBlogShow
}