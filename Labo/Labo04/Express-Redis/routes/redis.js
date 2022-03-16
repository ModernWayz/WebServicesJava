const express = require('express')
var router = express.Router()
const fetch = require('node-fetch')
const Redis = require('ioredis')
const redis = new Redis()

router.get('/:name', async (req, res) => {
	
	
})

module.exports = router