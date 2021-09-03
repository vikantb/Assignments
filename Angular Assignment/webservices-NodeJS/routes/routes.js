
const express = require('express');
const {getBlogs,getBlog,
        add,deleteBlog,editBlog} = require('../controller/controller');

const router = express.Router();

router.get('/api/blogs', getBlogs);
router.get('/api/blog/:id', getBlog);
router.post('/api/blog/add', add);
router.get('/api/blog/delete/:id', deleteBlog);
router.post('/api/blog/edit/:id', editBlog);
router.get('*',(req, res) => {
        res.sendFile('index.html')
})

module.exports =router