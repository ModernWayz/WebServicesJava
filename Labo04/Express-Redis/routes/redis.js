const express = require('express')
var router = express.Router()
const fetch = require('node-fetch')
const Redis = require('ioredis')
const redis = new Redis()

router.get('/:name', async (req, res) => {
	
	let name = req.params.name
	let repos_cached = await redis.lrange(name, 0, -1)

	if (repos_cached.length > 0) {
		console.log(name + ' is cached')
		res.json(repos_cached)
		
		return
	}

	console.log(name + ' is not cached')
	let response = await fetch(`https://api.github.com/users/${name}/repos`).then((t) => t.json())
	let repos = response.map(r => r.name)
	repos.map(r => redis.rpush(name, r))

	await redis.expire(name, 60)

	res.json(repos)
})

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

module.exports = router;
