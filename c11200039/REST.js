var express = require('express');
var bodyparser = require('body-parser');
var fs = require('fs');
var mysql = require('mysql');
var app = express();


app.use(bodyparser.json());

const conn = mysql.createConnection({
    host : '127.0.0.1',
    user : 'root',
    password : '',
    database : 'pendaftaran_turnamen'
});

conn.connect(function(err){
    if (err) throw err;
    console.log("MySQL connected.....");
});

app.get('/admin', function(req,res){
    console.log('Menerima GET request /admin');
    let sql = "SELECT * FROM admin";
    let query = conn.query(sql,function(err,result){
        if (err) throw err;
        res.send(JSON.stringify({
            "status" : 200,
            "error" : null,
            "response" : result
        }));
    });
});

app.get('/GameIndividu', function(req,res){
    console.log('Menerima GET request /Game-Individu');
    let sql = "SELECT * FROM game_individu";
    let query = conn.query(sql,function(err,result){
        if (err) throw err;
        res.send(JSON.stringify({
            "status" : 200,
            "error" : null,
            "response" : result
        }));
    });
});

app.get('/GameKelompok', function(req,res){
    console.log('Menerima GET request /Game-Kelompok');
    let sql = "SELECT * FROM game_kelompok";
    let query = conn.query(sql,function(err,result){
        if (err) throw err;
        res.send(JSON.stringify({
            "status" : 200,
            "error" : null,
            "response" : result
        }));
    });
});



app.get('/PesertaGameIndividuDiterima', function(req,res){
    console.log('Menerima GET request /PesertaGameIndividu-Diterima');
    let sql = "SELECT * FROM peserta_individu pi inner join game_individu gi on gi.idgame = pi.idpeserta WHERE status = 1";
    let query = conn.query(sql,function(err,result){
        if (err) throw err;
        res.send(JSON.stringify({
            "status" : 200,
            "error" : null,
            "response" : result
        }));
    });
});
app.get('/PesertaGameIndividuDitolak', function(req,res){
    console.log('Menerima GET request /PesertaGameIndividuDitolak');
    let sql = "SELECT * FROM peserta_individu pi inner join game_individu gi on gi.idgame = pi.idpeserta WHERE status = 2";
    let query = conn.query(sql,function(err,result){
        if (err) throw err;
        res.send(JSON.stringify({
            "status" : 200,
            "error" : null,
            "response" : result
        }));
    });
});
app.get('/PesertaGameIndividuPending', function(req,res){
    console.log('Menerima GET request /PesertaGameIndividu-Diterima');
    let sql = "SELECT * FROM peserta_individu pi inner join game_individu gi on gi.idgame = pi.idpeserta WHERE status = 0";
    let query = conn.query(sql,function(err,result){
        if (err) throw err;
        res.send(JSON.stringify({
            "status" : 200,
            "error" : null,
            "response" : result
        }));
    });
});

app.get('/PesertaGameIndividuWin', function(req,res){
    console.log('Menerima GET request /PesertaGameIndividu-Win');
    let data = {win: req.body.win};
    let sql = "SELECT * FROM peserta_individu pi inner join game_individu gi on gi.idgame = pi.idpeserta WHERE status = 1 AND win = '" + data.win + "';";
    let query = conn.query(sql,function(err,result){
        if (err) throw err;
        res.send(JSON.stringify({
            "status" : 200,
            "error" : null,
            "response" : result
        }));
    });
});

app.get('/teamTerima', function(req,res){
    console.log('Menerima GET request /Team');
    let sql = "SELECT * FROM team t inner join peserta_kelompok pk on t.idteam = pk.team_idteam inner join game_kelompok gk on t.game_kelompok_idgame=gk.idgame where status =1 group by nama_team  ;";
    let query = conn.query(sql,function(err,result){
        if (err) throw err;
        res.send(JSON.stringify({
            "status" : 200,
            "error" : null,
            "response" : result
        }));
    });
});

app.get('/teamPending', function(req,res){
    console.log('Menerima GET request /Team');
    let sql = "SELECT * FROM team t inner join game_kelompok g on t.game_kelompok_idgame=g.idgame where status = 0; ";
    let query = conn.query(sql,function(err,result){
        if (err) throw err;
        res.send(JSON.stringify({
            "status" : 200,
            "error" : null,
            "response" : result
        }));
    });
});


app.get('/TeamDiterima', function(req,res){
    console.log('Menerima GET request /Team-Diterima');
    let sql = "SELECT * FROM team t inner join peserta_kelompok pk on t.idteam = pk.team_idteam WHERE status = 1 ";
    let query = conn.query(sql,function(err,result){
        if (err) throw err;
        res.send(JSON.stringify({
            "status" : 200,
            "error" : null,
            "response" : result
        }));
    });
});

app.get('/TeamDitolak', function(req,res){
    console.log('Menerima GET request /TeamDitolak');
    let sql = "SELECT * FROM team t inner join game_kelompok g on t.game_kelompok_idgame=g.idgame where status = 2;";
    let query = conn.query(sql,function(err,result){
        if (err) throw err;
        res.send(JSON.stringify({
            "status" : 200,
            "error" : null,
            "response" : result
        }));
    });
});


app.get('/TeamWin', function(req,res){
    console.log('Menerima GET request /Team-Win');
    let data = {win: req.body.win};
    let sql = "SELECT * FROM team t inner join peserta_kelompok pk on t.idteam = pk.team_idteam WHERE status = 1 AND win = '" + data.win + "';";
    let query = conn.query(sql,function(err,result){
        if (err) throw err;
        res.send(JSON.stringify({
            "status" : 200,
            "error" : null,
            "response" : result
        }));
    });
});

app.post('/insertAdmin',function(req,res){
    console.log('Menerima POST request /insertAdmin');
    console.log(req.body);
    let data = {username: req.body.username,
                password: req.body.password
                };
    let sql = "INSERT INTO admin (username,password) VALUES ('" + data.username + "','" + data.password + "')";
    let query = conn.query(sql,data,function(err,result){
        if (err) throw err;
        res.send(JSON.stringify(
            {
                "status" : 200,
                "error"  : null,
                "response" : result
            }
        ))
    });
});

app.post('/insertGameIndividu',function(req,res){
    console.log('Menerima POST request /insertGameIndividu');
    console.log(req.body);
    let data = {nama_game: req.body.nama_game};
    let sql = "INSERT INTO game_individu (nama_game) VALUES ('" + data.nama_game + "')";
    let query = conn.query(sql,data,function(err,result){
        if (err) throw err;
        res.send(JSON.stringify(
            {
                "status" : 200,
                "error"  : null,
                "response" : result
            }
        ))
    });
});

app.post('/insertGameKelompok',function(req,res){
    console.log('Menerima POST request /insertGameKelompok');
    console.log(req.body);
    let data = {nama_game: req.body.nama_game};
    let sql = "INSERT INTO game_kelompok (nama_game) VALUES ('" + data.nama_game + "')";
    let query = conn.query(sql,data,function(err,result){
        if (err) throw err;
        res.send(JSON.stringify(
            {
                "status" : 200,
                "error"  : null,
                "response" : result
            }
        ))
    });
});

app.post('/insertPemain_individu',function(req,res){
    console.log('Menerima POST request /insertPemain_individu');
    console.log(req.body);
    let data = {nama: req.body.nama,
                alamat: req.body.alamat,
                tanggal_lahir: req.body.tanggal_lahir,
                no_handphone: req.body.no_handphone,
                nrp: req.body.nrp,
                program_studi: req.body.program_studi,
                angkatan: req.body.angkatan,
                idgame: req.body.idgame};
    let sql = "INSERT INTO peserta_individu (nama,alamat,tanggal_lahir,no_handphone,nrp,program_studi,angkatan,win,lose,game_individu_idgame,status) VALUES ('" + data.nama + "','" + data.alamat + "','" + data.tanggal_lahir + "','" + data.no_handphone + "','" + data.nrp + "','" + data.program_studi + "','" + data.angkatan + "',0,0,'" + data.idgame + "',0);";
    let query = conn.query(sql,data,function(err,result){
        if (err) throw err;
        res.send(JSON.stringify(
            {
                "status" : 200,
                "error"  : null,
                "response" : result
            }
        ))
    });
});

app.post('/insertTeam',function(req,res){
    console.log('Menerima POST request /insertTeam');
    console.log(req.body);
    let data = {nama_team: req.body.nama_team,
                idgame: req.body.idgame};
    let sql = "INSERT INTO team (nama_team,win,lose,game_kelompok_idgame,status) VALUES ('" + data.nama_team + "',0,0,'" + data.idgame + "',0)";
    let query = conn.query(sql,data,function(err,result){
        if (err) throw err;
        res.send(JSON.stringify(
            {
                "status" : 200,
                "error"  : null,
                "response" : result
            }
        ))
    });
});

// app.post('/insertPemain_kelompok',upload.single('avatar'),function(req,res){
//     console.log('Menerima POST request /insertPemain_kelompok');
//     console.log(req.body);
//     let data = {nama: req.body.nama,
//                 alamat: req.body.alamat,
//                 tanggal_lahir: req.body.tanggal_lahir,
//                 no_handphone: req.body.no_handphone,
//                 nrp: req.body.nrp,
//                 program_studi: req.body.program_studi,
//                 angkatan: req.body.angkatan,
//                 idteam: req.body.idteam};
//     let sql = "INSERT INTO peserta_kelompok (nama,alamat,tanggal_lahir,no_handphone,nrp,program_studi,angkatan,team_idteam) VALUES ('" + data.nama + "','" + data.alamat + "','" + data.tanggal_lahir + "','" + data.no_handphone + "','" + data.nrp + "','" + data.program_studi + "','" + data.angkatan + "','" + data.idteam + "');";
//     let query = conn.query(sql,data,function(err,result){
//         if (err) throw err;
//         res.send(JSON.stringify(
//             {
//                 "status" : 200,
//                 "error"  : null,
//                 "response" : result
//             }
//         ))
//     });
// });

app.post('/updateStatusDiterima', function(req,res){
    console.log('Menerima POST request /updateStatusPeserta_individu-Diterima');
    let data = { idpeserta: req.body.idpeserta};
    let sql = "UPDATE peserta_individu SET status = 1 WHERE idpeserta = "+ data.idpeserta +";";
    let query = conn.query(sql,function(err,result){
        if (err) throw err;
        res.send(JSON.stringify({
            "status" : 200,
            "error" : null,
            "response" : result
        }));
    });
});

app.post('/updateStatusTeamDiterima', function(req,res){
    console.log('Menerima POST request /updateStatusTeam-Diterima');
    let data = { idteam: req.body.idteam};
    let sql = "UPDATE team SET status = 1 WHERE idteam = "+ data.idteam +";";
    let query = conn.query(sql,function(err,result){
        if (err) throw err;
        res.send(JSON.stringify({
            "status" : 200,
            "error" : null,
            "response" : result
        }));
    });
});

app.post('/updateStatusTeamDitolak', function(req,res){
    console.log('Menerima POST request /updateStatusTeam-Ditolak');
    let data = { idteam: req.body.idteam};
    let sql = "UPDATE team SET status = 2 WHERE idteam = "+ data.idteam +";";
    let query = conn.query(sql,function(err,result){
        if (err) throw err;
        res.send(JSON.stringify({
            "status" : 200,
            "error" : null,
            "response" : result
        }));
    });
});

app.post('/updateTolakPeserta', function(req,res){
    console.log('Menerima POST request /updateStatusTeam-Ditolak');
    let data = { idpeserta: req.body.idpeserta};
    let sql = "UPDATE peserta_individu SET status = 2 WHERE idpeserta = "+ data.idpeserta +";";
    let query = conn.query(sql,function(err,result){
        if (err) throw err;
        res.send(JSON.stringify({
            "status" : 200,
            "error" : null,
            "response" : result
        }));
    });
});


app.post('/updateWinLosePeserta_individu', function(req,res){
    console.log('Menerima POST request /updateWinLosePeserta_individu');
    let data = { win: req.body.win,
                lose: req.body.lose,
                idpeserta: req.body.idpeserta};
    let sql = "UPDATE peserta_individu SET `win` = win+ '" + data.win + "', `lose` = lose +'" + data.lose + "' WHERE `peserta_individu`.`idpeserta` = '" + data.idpeserta + "';";

    let query = conn.query(sql,function(err,result){
        if (err) throw err;
        res.send(JSON.stringify({
            "status" : 200,
            "error" : null,
            "response" : result
        }));
    });
});

app.post('/updateWinLoseTeam', function(req,res){
    console.log('Menerima POST request /updateWinLoseTeam');
    let data = { win: req.body.win,
                lose: req.body.lose,
                idteam: req.body.idteam};
    let sql = "UPDATE team SET win = win + " + data.win + ", lose = lose + " + data.lose + " WHERE idteam = '"+ data.idteam +"';";
    let query = conn.query(sql,function(err,result){
        if (err) throw err;
        res.send(JSON.stringify({
            "status" : 200,
            "error" : null,
            "response" : result
        }));
    });
});

app.post('/login', function (req, res) {
    console.log('Menerima POST request /login');
    let data = { username: req.body.username,
        password: req.body.password};
  
    let sql = "SELECT * FROM admin WHERE username = '" + data.username + "' AND password = '" + data.password + "'";
  
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

var server = app.listen(7000,function(){
    var host = server.address().address;
    var port = server.address().port;
    console.log("Express app listening at http://%s:%s", host,port);
});
