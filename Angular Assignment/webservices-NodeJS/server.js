
const express = require('express')
const router = require('./routes/routes');
const cors = require('cors');
const path = require('path');

const app = express()

app.use(express.static(path.join(__dirname, '../frontEnd-Angular/dist/frontEnd-Angular')))

app.use(express.json())
app.use(cors());

app.use('/', router);

// listen to port
app.listen(4545,()=>console.log(`Listening to localhost:4545`))