const express = require('express')
var router = express.Router()
const fetch = require('node-fetch')
const Redis = require('ioredis')
const redis = new Redis()

router.get('/endpoint', async (req, res) => {

    let ip = req.ip
    await redis.incr(ip)
    await redis.expire(ip, 60)

    count = await redis.get(req.ip)

    if(count < 6) {
	    res.json({'status' : 'OK'})
    }
    else {
        res.json({'warning' : '5 calls in 60 seconds'})
    }
})

module.exports = router