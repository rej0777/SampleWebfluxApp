


POST http://localhost:8080/math/square/5
return 5*5
{   "date": "2023-06-17T13:04:18.577+00:00",   "output": 25 }

------------
POST http://localhost:8080/math/table/5
return table 5*n10
{       "date": "2023-06-17T13:55:03.121+00:00",       "output": 5 *n10  },
--------
get  http://localhost:8080/job/serch
Params 
count 8
pagr 3
[    8,    3]

----------------------
REACTIVE
get  http://localhost:8080/reactiveMath/square/5
{  "date": "2023-06-17T15:02:45.176+00:00",   "output": 25   }
---------------------
REACTIVE
http://localhost:8080/reactiveMath/table/5/stream
data:{"date":"2023-06-17T15:06:52.011+00:00","output":5*N5}
---------
post http://localhost:8080/reactiveMath/multiply
Headers klucz wartość
	body json  {"first": 35, "second":2
--------------------

http://localhost:8080/reactiveMath/square/11/MonoError
{"errorCode": 100,  "input": 6,  "message": "dozwolony zakrs pomiędzy 1 - 20"}

-------------
http://localhost:8080/reactiveMath/square/15/v2 
15*15


