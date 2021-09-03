
const express = require('express');
const { addBlog,getAllBlogs,
        createNewBlog,blogShow,
        deleteBlog, 
        editBlog,editBlogShow} = require('../controller/controller');

const router = express.Router();

router.get('/', getAllBlogs);
router.get('/add', addBlog);
router.post('/add', createNewBlog);
router.get('/blog/:id', blogShow);
router.get('/delete/:id', deleteBlog);
router.get('/edit/:id', editBlogShow);
router.post('/edit/:id', editBlog);

module.exports =router