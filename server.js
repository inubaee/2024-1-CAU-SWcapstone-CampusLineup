const express = require("express");
const cors = require("cors");

const app = express();
app.use(cors());

const path = require("path");

app.use(express.static(path.join(__dirname)));

app.listen(3000, () => {
  console.log("Server is running on port 3000!");
});
