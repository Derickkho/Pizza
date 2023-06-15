var express = require('express');
var fs = require('fs');
var bodyparser = require('body-parser');
var mysql = require('mysql');
var app = express();
const multer  = require('multer');
const path = require("path");

app.use(bodyparser.json());

const conn = mysql.createConnection({
    host : '127.0.0.1',
    user : 'root',
    password : '',
    database : 'pizzadatabase'
});

conn.connect(function(err){
    if (err) throw err;
    console.log("MySQL connected...")
});

app.post('/signup',function(req,res){
    console.log('Menerima POST request /signup');
    console.log(req.body.nama)
    let data = {Email:req.body.email,
                Password:req.body.pass,
                Account:req.body.nama};
    let sql = "INSERT INTO user SET ?";
    let query = conn.query(sql,data,function(err,result){
        if (err) throw err;
        res.send(JSON.stringify(
            {
                "status"    : 200,
                "error"     : null,
                "response"  : result
            }
        ))
    });
});

app.post('/login', function (req, res) {
    console.log('Menerima POST request /login');
    let data = { account: req.body.account,
        password: req.body.pass};
  
    let sql = "SELECT account,password FROM user WHERE account = '" + data.account + "' AND password = '" + data.password + "'";
    conn.query(sql, function (error, results) {
      if (error) {
        console.error('Error executing query:', error);
        res.status(500).json({ message: 'Internal server error' });
      } else {
        if (results.length > 0) {
          res.status(200).json({ message: 'Login successful' });
        } else {
          res.status(401).json({ message: 'Login failed' });
        }
      }
    });
  });

app.post('/order',function(req,res){
    console.log('Menerima POST request /order');
    console.log(req.body.nama)
    let data = {Account:req.body.nama,
                PizzaType:req.body.pizza};
    let sql = "INSERT INTO pesanan (account,time,pizzatype) VALUES ('"+req.body.nama+"', CURRENT_TIMESTAMP(), '"+req.body.pizza+"')";
    let query = conn.query(sql,data,function(err,result){
        if (err) throw err;
        res.send(JSON.stringify(
            {
                "status"    : 200,
                "error"     : null,
                "response"  : result
            }
        ))
    });
});
  
const storage = multer.diskStorage({
    destination: './upload/images',
    filename: (req, file, cb) => {
        return cb(null, `${file.fieldname}_${Date.now()}${path.extname(file.originalname)}`)
    }
})

const upload = multer({
    storage: storage,
    limits: {
        fileSize: 1000000
    }
})
app.use('/profile', express.static('upload/images'));
app.put("/preparing", upload.single('profile'), (req, res) => {
    console.log('Menerima POST request /upload');
    const no = req.body.no;
    const link = req.file.filename;
    const pizzamaker = req.body.maker;
    console.log(link)
    let sql = "UPDATE pesanan SET pizzafile = ?,pizzamaker = ? WHERE referralnumber = ?";
    let query = conn.query(sql,[link,pizzamaker,no],function(err,result){
        if (err) throw err;
        res.send(JSON.stringify(
            {
                "status"    : 200,
                "error"     : null,
                "response"  : result,
            }
        ))
    });
    
})

app.get('/openpizza',function(req,res){
    console.log('Menerima GET request /openpizza');
    const no = req.body.no;
    let sql = "SELECT pizzafile FROM pesanan WHERE referralnumber = ?";
    let query = conn.query(sql,no, function(error, results){
        if (error) {
            console.log(error);
        } else {
            const photoFilename = results[0].pizzafile;
            const photoPath = path.join("/Users/Derick/Documents/NodeJS", '/upload/images', photoFilename);
            fs.readFile(photoPath, (err, data) => {
                if (err) throw err;
                console.log(photoPath);
                res.send(JSON.stringify(
                    {
                        "status"    : 200,
                        "error"     : null,
                        "response"  : data,
        
                    }
                ))
            });
        }
    });
});  

app.put('/rate',function(req,res){
    console.log('Menerima POST request /rate');
    const id = req.body.no;
    const nama = req.body.nama
    const nilai = req.body.nilai;
    let sql = "UPDATE pesanan SET rating = ? WHERE referralnumber = ? AND account = ?";
    let query = conn.query(sql,[nilai,id,nama],function(err,result){
        if (err) throw err;
        res.send(JSON.stringify(
            {
                "status"    : 200,
                "error"     : null,
                "response"  : result
            }
        ))
    });
});

app.get('/seerate/:pizzamaker',function(req,res){
    console.log('Menerima GET request /seerate');
    let sql = "SELECT AVG(rating) as rata from pesanan WHERE pizzamaker = '"+req.params.pizzamaker+"'";
    let query = conn.query(sql,function(err,result){
        if (err) throw err;
        res.send(JSON.stringify(
            {
                "status"    : 200,
                "error"     : null,
                "response"  : result
            }
        ))
    });
});

app.get('/history/:pizzamaker',function(req,res){
    console.log('Menerima GET request /history');
    let sql = "SELECT * from pesanan WHERE pizzamaker = '"+req.params.pizzamaker+"'";
    let query = conn.query(sql,function(err,result){
        if (err) throw err;
        res.send(JSON.stringify(
            {
                "status"    : 200,
                "error"     : null,
                "response"  : result
            }
        ))
    });
});

app.get('/ongoing',function(req,res){
    console.log('Menerima GET request /ongoing');
    const data = "";
        let sql = "SELECT * from pesanan WHERE pizzamaker = ?";
    let query = conn.query(sql,data,function(err,result){
        if (err) throw err;
        res.send(JSON.stringify(
            {
                "status"    : 200,
                "error"     : null,
                "response"  : result
            }
        ))
    });
});

var server = app.listen(7000,function(){
    var host = server.address().address;
    var port = server.address().port;
    console.log("Express app listening at http://%s:%s", host, port);
});