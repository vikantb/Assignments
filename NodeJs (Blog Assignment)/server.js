
const express = require('express')
const router = require('./routes/routes');
const bodyParser = require('body-parser');

const app = express()

app.set('view engine', 'ejs');

app.use(bodyParser.urlencoded())

app.use('/', router);

// listen to port
app.listen(4545,()=>console.log(`Listening to localhost:4545`))