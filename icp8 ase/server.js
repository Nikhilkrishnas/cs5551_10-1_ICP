var express= require('express');
var app= express();
const Clarifai = require('clarifai');
const app1 = new Clarifai.App({
    apiKey: 'c42e2566765c499cb36fb1d47b73424d'
   });
   app.get("/getDetails", (req, res, next) => {
    app1.models.predict("eeed0b6733a644cea07cf4c60f87ebb7",req.query.url).then(
        function (response) {
            //console.log("hi");
           // console.log(response);
            var x="";
          // var result=response.data.rawData;
            //for(var results in result){
              //  x=x+results.w3c.name+":"+results.w3c.hex+"\t"+results.valueOf;
            //}
            res.send(response);
        },
        function (err) {
            // there was an error
            console.log(err);
        }
    );
});
app.use(express.static(__dirname+'/home'));

app.listen(3000);