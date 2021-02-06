--[[
Author: your name
Date: 2021-01-29 17:01:49
LastEditTime: 2021-01-30 18:34:47
LastEditors: Please set LastEditors
Description: In User Settings Edit
FilePath: \sckill-redis\src\main\resources\seckill.lua
--]]

local falseRet = "0"
local n = tonumber(ARGV[1])
local key = KEYS[1]
local goodsInfo = redis.call("HMGET",key,"total","alloc")
local total = tonumber(goodsInfo[1])
local alloc = tonumber(goodsInfo[2])
if not total then
    return falseRet
end
if total >= alloc + n  then
    local ret = redis.call("HINCRBY",key,"alloc",n)
    return tostring(ret)
end
return falseRet
